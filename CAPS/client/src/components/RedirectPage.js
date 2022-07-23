import React, { Component } from "react";

export default class RedirectPage extends Component{
    constructor(props){
        super(props);
    }
    componentDidMount(){
        window.location.replace('http://localhost:8080/');
    }

    render(){
        return(
            <div>Redirect</div>
        )
    }
       
}