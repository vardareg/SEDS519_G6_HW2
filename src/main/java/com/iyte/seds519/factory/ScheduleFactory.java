package com.iyte.seds519.factory;

import com.iyte.seds519.decorator.LoggingDecorator;
import com.iyte.seds519.decorator.ValidationDecorator;
import com.iyte.seds519.schedule.BasicScheduler;
import com.iyte.seds519.schedule.Scheduler;
import com.iyte.seds519.strategy.TimeSelectionStrategy;

public class ScheduleFactory {

    public static Scheduler create(ScheduleType type, TimeSelectionStrategy strategy) {
        Scheduler base = new BasicScheduler(strategy);

        // Decorate with validation + logging
        Scheduler decorated = new LoggingDecorator(new ValidationDecorator(base));

        // Future: add different behavior based on type
        return decorated;
    }
}
