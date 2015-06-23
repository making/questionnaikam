var React = require('react');
var App = require('./components/App.jsx');

window.render = function (data) {
    React.render(
        <App data={data}/>,
        document.getElementById('example')
    );
};