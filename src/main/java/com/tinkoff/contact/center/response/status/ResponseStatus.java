package com.tinkoff.contact.center.response.status;

public enum ResponseStatus {
    SUCCESS {
        public String toString() {
            return "success";
        }
    },
    FAILURE {
        public String toString() {
            return "fail";
        }
    }
}
