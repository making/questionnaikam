var React = require('react');
var request = require('superagent');

var QuestionnaireItem = React.createClass({
    getInitialState: function () {
        return {item: this.props.item, subscribed: false};
    },
    vote: function () {
        if (this.props.voted) {
            alert('Already voted!');
            return;
        }
        this.props.client.send('/app/vote/' + this.state.item.itemId, {});
        this.props.setVoted();
    },
    render: function () {
        if (this.props.connected && !this.state.subscribed) {
            var item = this.state.item;
            this.props.client.subscribe('/topic/questionnaires/' + item.itemId,
                function (message) {
                    var q = JSON.parse(message.body);
                    var requireReload = (this.props.sum === 0);
                    if (requireReload) {
                        window.location.reload();
                    }
                    item.value = q.value;
                    this.props.updateItem(this.props.index, item);
                }.bind(this));
            this.state.subscribed = true;
        }
        return (<tr>
            <td>{this.state.item.label}</td>
            <td>
                <button onClick={this.vote} disabled={this.props.voted}>Vote</button>
            </td>
            <td>{this.state.item.value}</td>
        </tr>);
    }
});

module.exports = QuestionnaireItem;