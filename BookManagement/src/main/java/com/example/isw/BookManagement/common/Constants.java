package com.example.isw.BookManagement.common;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final String ADMIN = "ADMIN";

    public static final String PARENT = "Parent";
    public static final String STUDENT = "Student";
    public static final String OTHER = "Other";

    public static final List<String> TYPES = new ArrayList<String>(){{
       add(PARENT);
       add(STUDENT);
       add(OTHER);
    }};

    public static final Integer BOOK_AVAILABLE = 1;
    public static final Integer BOOK_ISSUED = 2;

    public static final Integer BOOK_NOT_RETURNED = 0;
    public static final Integer BOOK_RETURNED = 1;


}
