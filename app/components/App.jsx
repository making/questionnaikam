var React = require('react');
var Questionnaires = require('./Questionnaires.jsx');

var App = React.createClass({
    render: function () {
        return (<Questionnaires data={this.props.data} />);
    }
});

module.exports = App;