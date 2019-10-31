package com.exabeam.dl.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.extension.ExtendWith;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@ExtendWith(KnownIssueExtension.class)
public @interface KnownIssue {
    String description()
            default "Please set the reason for the expected failure like: @KnownIssue(description = \"LMS-12345\")";

    String beforeDate();
}
