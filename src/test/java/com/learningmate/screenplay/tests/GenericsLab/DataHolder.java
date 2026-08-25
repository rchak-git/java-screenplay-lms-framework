package com.learningmate.screenplay.tests.GenericsLab;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DataHolder <T>{

    private T content;

    public DataHolder(T inputContent)
    {

        this.content = inputContent;


    }

    public T getContent()
    {

        return content;
    }

    public boolean hasData ()
    {
        if (this.content != null)
            return true;
        else
            return false;

    }





}
