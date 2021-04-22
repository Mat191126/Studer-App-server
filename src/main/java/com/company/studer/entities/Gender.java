package com.company.studer.entities;

public enum Gender {
    MALE {
        public String toString() {
            return "Male";
        }
    },
    FEMALE {
        public String toString() {
            return "Female";
        }
    },
    OTHER {
        public String toString() {
            return "Other";
        }
    },
    NOT_CHOSEN {
        public String toString() {
            return "Not chosen";
        }
    }
}
