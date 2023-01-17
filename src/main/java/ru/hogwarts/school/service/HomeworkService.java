package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;
@Service
public class HomeworkService {
    Logger logger = LoggerFactory.getLogger(HomeworkService.class);
public Integer integerValue(){
    logger.debug("Was invoked method for add a integer value");
    return Stream
            .iterate(1, a -> a +1)
            .limit(1_000_000)
            .reduce(0, (a, b) -> a + b);
}
}
