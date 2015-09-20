package com.gyosh.worker;

import java.util.List;

public interface Task {
    List<List<String>> exec(List<List<String>> doc, Parameter param);
}
