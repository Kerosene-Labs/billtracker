package com.kerosenelabs.billtracker.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class NoReplyMailService(val javaMailSender: JavaMailSender) {
    fun sendTestEmail(to: String?, subject: String?, text: String?) {
        val message = SimpleMailMessage()
        message.from = "noreply@kerosenelabs.com"
        message.setTo(to)
        message.subject = subject
        message.text = text
        javaMailSender.send(message)
    }
}
