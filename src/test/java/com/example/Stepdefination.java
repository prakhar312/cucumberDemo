package com.example;

import java.io.IOException;

import com.example.TestComponents.BaseTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class Stepdefination extends BaseTest{

    public LoginPage launch;
    public ProductPage homepage;
    @Given("User try to login the website")
    public void User_landed_on_site() throws IOException{

        launch=launchApplication();

    }
    @When("^user enters the username(.+) and password(.+)$")
    public void loggedin_username_and_password(String username, String password){
        homepage=launch.Login(username, password);
    }
    
}
