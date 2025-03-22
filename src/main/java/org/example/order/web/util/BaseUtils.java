package org.example.order.web.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BaseUtils {
    public boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public boolean isEmpty(List<?> items) {
        return items == null || items.isEmpty();
    }

    public boolean isEmpty(Object l) {
        return l == null;
    }
}
