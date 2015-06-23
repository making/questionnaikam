var React = require('react');
var QuestionnaireItem = require('./QuestionnaireItem.jsx');
var PieChart = require("react-chartjs").Pie;

var Questionnaire = React.createClass({
    getInitialState: function () {
        return {title: this.props.q.title, items: this.props.q.items, voted: false};
    },
    updateItem: function (i, item) {
        var items = this.state.items;
        items[i] = item;
        this.setState({title: this.state.title, items: items, voted: this.state.voted});
    },
    setVoted: function () {
        this.setState({title: this.state.title, items: this.state.items, voted: true});
    },
    render: function () {
        var sum = this.state.items.map(function (item) {
            return item.value;
        }).reduce(function (x, y) {
            return x + y;
        });
        var d = this.state.items.map(function (item, i) {
            return (<QuestionnaireItem key={item.label}
                                       item={item}
                                       index={i}
                                       sum={sum}
                                       updateItem={this.updateItem}
                                       client={this.props.client}
                                       connected={this.props.connected}
                                       voted={this.state.voted}
                                       setVoted={this.setVoted}/>)
        }.bind(this));
        return (<div>
            <h2>{this.props.q.title}</h2>
            <table>{d}</table>
            <PieChart data={this.state.items}/>
        </div>);
    }
});

module.exports = Questionnaire;