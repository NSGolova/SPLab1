package com.radulov.study;

import java.util.ArrayList;

public class Table {
    private ArrayList<String> columns = new ArrayList<>();
    private ArrayList<Long> keys = new ArrayList<>();
    private ArrayList<EnumField> fStrs = new ArrayList<>();

    public Table(String[] columns, Long[] keyStr, EnumField[] fStr) throws TableFormatException
    {
        if (columns.length == 2)
        {
            for (int i=0; i<columns.length; i++)
            {
                this.columns.add(columns[i]);
            }
        }
        else
        {
            throw new TableFormatException(" WrongTableFormat! Number of columns must be 2!");
        }

        if (keyStr.length == fStr.length)
        {
            for (int i = 0; i < keyStr.length; i++)
            {
                keys.add(keyStr[i]);
                fStrs.add(fStr[i]);
            }
        }
        else
        {
            throw new TableFormatException("Number of keys dont equels number of functional fields!");
        }
    }

    // вибірка за прямою адресою елемента № nElm
    public Row getElement(int nElm)
    {
        return new Row(keys.get(nElm), fStrs.get(nElm));
    }

    // включення за прямою адресою в позицію(адресу) № nElm
    public void addElement(Long keyStr, EnumField fStr, int nElm)
    {
        if (!isKey(keyStr))
        {
            keys.add(nElm,keyStr);
            fStrs.add(nElm, fStr);
        }
        else
        {
            System.out.println("Element with this key '" + keyStr + "' is in this table!!!");
            System.out.println("Element not added!");
        }

    }

    // включення за прямою адресою в позицію(адресу) № nElm
    public void addElement(Row row, int nElm)
    {
        this.addElement(row.getKey(), row.getFuncField(), nElm);
    }

    // вилучення за прямою адресою елемента № nElm
    public void deleteElement(int nElm)
    {
        keys.remove(nElm);
        fStrs.remove(nElm);
    }

    // корекція за прямою адресою елемента № nElm
    public void updateElement(Long keyStr, EnumField fStr, int nElm)
    {
        if ((isKey(keyStr)) & (keys.get(nElm) != keyStr))
        {
            System.out.println("Correction is impossible! Element with this key is already in table!");
        }
        else
        {
            keys.remove(nElm);
            fStrs.remove(nElm);
            keys.add(nElm, keyStr);
            fStrs.add(nElm, fStr);
        }
    }

    // вибірка за лінійним пошуком по ключу
    public Row selectByLin(Long key)
    {
        Row row = new Row();
        for (int i = 0; i < keys.size(); i++)
        {
            if (keys.get(i) == key)
            {
                row.setKey(key);
                row.setFuncField(fStrs.get(i));
                return row;
            }
        }
        return row;
    }

    // вибірка за лінійним пошуком по функціональному полю
    public Row[] selectByLin(EnumField day)
    {
        ArrayList<Row> tempRows = new ArrayList<>();
        for (int i=0; i < fStrs.size(); i++)
        {
            if (fStrs.get(i) == day)
            {
                tempRows.add(new Row(keys.get(i), day));
            }
        }
        Row[] rows = new Row[tempRows.size()];
        for (int i = 0; i < rows.length; i++)
        {
            rows[i] = tempRows.get(i);
        }
        return rows;
    }

    //сортування таблиці по ключу
    public void sort()
    {
        for (int i=0; i < (keys.size() - 1); i++)
        {
            int minIndex = i;
            for (int j=i; j<keys.size(); j++)
            {
                if (keys.get(j) < keys.get(minIndex))
                {
                    minIndex = j;
                }
            }

            if (minIndex != i)
            {
                Long minItem = keys.get(minIndex);
                keys.set(minIndex, keys.get(i));
                keys.set(i, minItem);
            }
        }
    }

    // вибірка за двійковим пошуком по ключу
    public Row selectKeyByBin(Long key)
    {
        sort();
        Row row = new Row();
        if (keys.size() == 0)
        {
            return row;
        }
        if (keys.get(0) > key)
        {
            return row;
        }
        if (keys.get(keys.size()-1) < key)
        {
            return row;
        }
        int leftIndex = 0;
        int rightIndex = keys.size();
        int mid;

        while (leftIndex < rightIndex)
        {
            mid = leftIndex + (rightIndex - leftIndex) / 2;

            if (key <= keys.get(mid))
            {
                rightIndex = mid;
            }
            else
            {
                leftIndex = mid + 1;
            }
        }

        if (keys.get(rightIndex) == key)
        {
            row.setKey(key);
            row.setFuncField(fStrs.get(rightIndex));
            return row;
        }
        else
        {
            return row;
        }
    }

    //is key in this table ?
    private boolean isKey(Long key)
    {
        for (int i=0; i<keys.size(); i++)
        {
            if (keys.get(i).equals(key))
                return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        String result = "";

        for (int i = 0; i < columns.size(); i++)
        {
            result += columns.get(i) + "  |  ";
        }
        result += "\n---------------------\n";

        for (int i=0; i < keys.size(); i++)
        {
            result += " " + keys.get(i) + "   |   " + fStrs.get(i) + "\n";
        }
        result += "---------------------\n";

        return result;
    }
}




