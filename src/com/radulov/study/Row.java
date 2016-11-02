package com.radulov.study;

/**
 * Created by golova on 11/2/16.
 */
public class Row
{
    long key;
    EnumField funcField;

    public Row() {}

    public Row(long key, EnumField funcField)
    {
        this.key = key;
        this.funcField = funcField;
    }

    public long getKey()
    {
        return key;
    }

    public void setKey(long key)
    {
        this.key = key;
    }

    public EnumField getFuncField()
    {
        return funcField;
    }

    public void setFuncField(EnumField funcField)
    {
        this.funcField = funcField;
    }

    @Override
    public String toString()
    {
       return "key = " + key + "; func = " + funcField;
    }


}
