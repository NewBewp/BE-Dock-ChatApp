package com.example.springbase.service;

import com.example.springbase.util.EmailSubjectEnum;
import com.example.springbase.util.TypeMailEnum;
import org.springframework.stereotype.Service;

@Service
public interface MailService {
    Boolean sendWithTemplate(String email, String content, EmailSubjectEnum subject, TypeMailEnum type);
}
