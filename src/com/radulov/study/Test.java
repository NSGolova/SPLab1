package com.radulov.study;

public class Test
{
    public static void main(String[] args)
    {
        try
        {
            String[] columns = {"Key","Day of week"};
            Long[] keys = {5L, 6L, 3L, 4L};
            EnumField[] days = {EnumField.SATURDAY, EnumField.WEDNESDAY, EnumField.THURSDAY, EnumField.WEDNESDAY};

            Table table = new Table(columns, keys, days);
            System.out.println(table);

            Row firstRow = table.getElement(0);
            System.out.println(firstRow);

            Long a = 5L;
            EnumField day = EnumField.MONDAY;

            table.addElement(a, day, 0);
            table.deleteElement(1);
            table.updateElement(a, day, 0);

            System.out.println(table);

            //linear search
            Row[] someRows1 = table.selectByLin(EnumField.WEDNESDAY);
            for (Row someRow : someRows1)
            {
                System.out.println(someRow);
            }

            Row someRow = table.selectByLin(1L);
            System.out.println(someRow);

            table.sort();
            System.out.println(table);

            //binaear search
            Row someAnotherRow = table.selectKeyByBin(3L);
            System.out.println(someAnotherRow);
        }
        catch (TableFormatException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
