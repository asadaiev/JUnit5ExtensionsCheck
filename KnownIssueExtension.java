package com.exabeam.dl.common;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;


import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.extension.ConditionEvaluationResult.disabled;
import static org.junit.jupiter.api.extension.ConditionEvaluationResult.enabled;

public class KnownIssueExtension implements ExecutionCondition {

    private static final Logger LOG = Logger.getLogger(KnownIssueExtension.class.getName());

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {

        final Optional<Method> testMethod = context.getTestMethod();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        final Date currentDate = new Date();
        Date targetDate = null;
        try {
            targetDate = format.parse(testMethod.get().getAnnotation(KnownIssue.class).beforeDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LOG.info("CURRENT DATE: " + currentDate );
        LOG.info("TARGET DATE FOR THE FIX: " + targetDate);

        if (testMethod.isPresent()
                && testMethod.get().isAnnotationPresent(KnownIssue.class) && currentDate.before(targetDate)) {
            return disabled(testMethod.get().getAnnotation(KnownIssue.class).description() + " \nExpected fix date: " + targetDate);
        }
        return enabled("");
    }
}




