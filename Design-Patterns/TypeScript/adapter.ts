class MailService {
    sendMail(message: string): void {
        console.log("Sent message \"%s\" through mail", message);
    }
}

class SMSService {
    sendSMS(message: string): void {
        console.log("Sent message \"%s\" through sms", message);
    }
}

class PostalService {
    sendPost(message: string): void {
        console.log("Sent message \"%s\" through post", message);
    }
}

interface MessageService {
    sendMessage(message: string): void;
}

class PostalMessageService implements MessageService {
    constructor(private readonly service: PostalService) {
        this.service = service;
    }

    sendMessage(message: string): void {
        this.service.sendPost(message);
    }
}


class MailMessageService implements MessageService {
    constructor(private readonly service: MailService) {
        this.service = service;
    }

    sendMessage(message: string): void {
        this.service.sendMail(message);
    }
}


class SMSMessageService implements MessageService {
    constructor(private readonly service: SMSService) {
        this.service = service;
    }

    sendMessage(message: string): void {
        this.service.sendSMS(message);
    }
}

const mailService = new MailService();
const smsService = new SMSService();
const postalService = new PostalService();

const services = [
    new MailMessageService(mailService),
    new PostalMessageService(postalService),
    new SMSMessageService(smsService)
];

const message = "Hello world";

// All services adapted to a common interface

services.forEach((service: MessageService) => {
    service.sendMessage(message);
});