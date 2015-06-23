var React = require('react');
var Questionnaire = require('./Questionnaire.jsx');
var request = require('superagent');
var Stomp = require('stompjs');

var Questionnaires = React.createClass({
    getInitialState: function () {
        var socket = new WebSocket('ws://' + location.host + '/endpoint');
        var client = Stomp.over(socket);
        client.debug = null;
        var state = {client: client, data: this.props.data, connected: false};
        client.connect({}, function () {
            state.connected = true;
            this.setState(state);
        }.bind(this));
        return state;
    },
    render: function () {
        var values = this.state.data.values.map(function (q) {
            return <Questionnaire key={q.id}
                                  q={q}
                                  client={this.state.client}
                                  connected={this.state.connected}/>;
        }.bind(this));
        return (<div>{values}</div>);
    }
});

module.exports = Questionnaires;