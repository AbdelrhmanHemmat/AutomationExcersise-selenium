package com.automationexercise.drivers;

import com.automationexercise.drivers.*;

public enum Browser {
    CHROME {
        @Override
        public AbstractDriver getDriverFactory() {
            return new ChromeFactory();
        }
    },
    EDGE {
        @Override
        public AbstractDriver getDriverFactory() {
            return new EdgeFactory();
        }
    },
    FIREFOX {
        @Override
        public AbstractDriver getDriverFactory() {
            return new FirefoxFactory();
        }
    },
    SAFARI {
        @Override
        public AbstractDriver getDriverFactory() {
            return new SafariFactory();
        }
    };

    public abstract AbstractDriver getDriverFactory();
}