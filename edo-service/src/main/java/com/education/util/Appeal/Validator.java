package com.education.util.Appeal;



import lombok.extern.log4j.Log4j2;
import model.dto.AppealDto;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Проверяет Appeal
 */
@Log4j2
public class Validator {
    private static String emailReg = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static String phoneReg = "/^(\\+?\\d{1,4}[\\s-])?(?!0+\\s+,?$)\\d{10}\\s*,?$";
    // Проверка поля appeal на наличие question
    public static void getValidateAppeal(AppealDto appeal) {
        List<String> err = new ArrayList<>();
        if (appeal.getQuestions().size() > 0) {
            appeal.getQuestions().forEach(question -> {
                if (question.getTheme() == null) {
                    err.add(String.format("Appeal with ID %s has question with ID %s where Theme is null",
                            appeal.getId(), question.getId()));
                }
                if (question.getSummary() == null) {
                    err.add(String.format("Appeal with ID %s has question with ID %s where Summary is null",
                            appeal.getId(), question.getId()));
                } else if (question.getSummary().length() > 200) {
                    err.add(String.format("Appeal with ID %s has question with ID %s where Summary has length more then 200 symbols",
                            appeal.getId(), question.getId()));
                }
            });
        } else {
            err.add(String.format("Appeal with ID %s has not question", appeal.getId()));
        }
        if (appeal.getSendingMethod() == null) {
            err.add(String.format("Appeal with ID %s has null SendingMethod", appeal.getId()));
        }
        if (appeal.getAuthors() != null) {
            appeal.getAuthors().forEach(author -> {
                if (author.getFirstName() == null) {
                    err.add(String.format("Appeal with ID %s has author with ID %s where firstName is null",
                            appeal.getId(), author.getId()));
                } else if (author.getFirstName().length() > 60) {
                    err.add(String.format("Appeal with ID %s has author with ID %s where firstName has length more then 60 symbols",
                            appeal.getId(), author.getId()));
                }
                if (author.getLastName() == null) {
                    err.add(String.format("Appeal with ID %s has author with ID %s where lastName is null",
                            appeal.getId(), author.getId()));
                } else if (author.getLastName().length() > 60) {
                    err.add(String.format("Appeal with ID %s has author with ID %s where lastName has length more then 60 symbols",
                            appeal.getId(), author.getId()));
                }
                if (author.getEmail() == null) {
                    err.add(String.format("Appeal with ID %s has author with ID %s where email is null",
                            appeal.getId(), author.getId()));
                } else {
                    Pattern emailPattern = Pattern.compile(emailReg);
                    Matcher emailMatcher = emailPattern.matcher(author.getEmail());
                    if (!emailMatcher.matches()) {
                        log.info("Email is not valid");
                        err.add(String.format("Appeal with ID %s has author with ID %s with Email is not valid",
                                appeal.getId(), author.getId()));
                    }
                }
                if (author.getMobilePhone() == null) {
                    err.add(String.format("Appeal with ID %s has author with ID %s where mobilePhone is null",
                            appeal.getId(), author.getId()));
                } else {
                    Pattern phonePattern = Pattern.compile(phoneReg);
                    Matcher phoneMatcher = phonePattern.matcher(author.getMobilePhone());
                    if (!phoneMatcher.matches()) {
                        log.info("Phone is not valid");
                        err.add(String.format("Appeal with ID %s has author with ID %s with mobilePhone is not valid",
                                appeal.getId(), author.getId()));
                    }
                }
            });
        }

        if (!err.isEmpty()){
            throw new AppealCustomException(err.toString());
        }
    }
}
