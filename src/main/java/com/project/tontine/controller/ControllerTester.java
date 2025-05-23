package com.project.tontine.controller;

public class ControllerTester
{
    public static boolean isValidEmail(String email)
    {

        return email.contains("@") || email.contains(".");
    }

    public static boolean notOnlyNumber(String string)
    {
        if(string == null)
        {
            return false;
        }

        return string.matches("\\+?[^0-9]+$");
    }
}
