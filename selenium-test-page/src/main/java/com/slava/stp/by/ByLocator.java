package com.slava.stp.by;

public static class ByLocator extends By {

        private static final String IDENTIFIER = "identifier";
        private static final String ID = "id";
        private static final String NAME = "name";
        private static final String XPATH = "xpath";
        private static final String LINK = "link";
        private static final String DOM = "dom";
        private static final String CSS = "css";

        private static final String XPATH_START_WITH = "//";
        private static final String DOM_START_WITH = "document";

        private static final String COMMAND_SPLITER = "=";

        private String locator;
        private org.openqa.selenium.By by;

        public ByLocator(String locator) {
            if (locator.startsWith(XPATH_START_WITH)) {
                locator = XPATH + COMMAND_SPLITER + locator;
            } else if (locator.startsWith(DOM_START_WITH)) {
                locator = DOM + COMMAND_SPLITER + locator;
            }
            this.locator = locator;

            int indexOf = locator.indexOf(COMMAND_SPLITER);
            if (indexOf != -1) {
                String type = locator.substring(0, indexOf).trim();
                String value = locator.substring(indexOf + 1);
                if (type.equals(IDENTIFIER)) {
                    by = By.identifier(value);
                } else if (type.equals(ID)) {
                    by = By.id(value);
                } else if (type.equals(NAME)) {
                    by = By.name(value);
                } else if (type.equals(XPATH)) {
                    by = By.xpath(value);
                } else if (type.equals(LINK)) {
                    by = By.linkText(value);
                } else if (type.equals(DOM)) {
                    by = By.dom(value);
                } else if (type.equals(CSS)) {
                    by = By.cssSelector(value);
                } else {
                    by = getDefaultTypeLocator(locator);
                }
            } else {
                by = getDefaultTypeLocator(locator);
            }
        }

        @Override
        public List<WebElement> findElements(SearchContext context) {
            return by.findElements(context);
        }

        @Override
        public WebElement findElement(SearchContext context) {
            try {
                return by.findElement(context);
            } catch (NoSuchElementException e) {
                throw new NoSuchElementException(getNoSuchElementExceptionMessage(), e);
            }
        }

        @Override
        public String toString() {
            return "By.locator: " + locator + " > " + by;
        }

        private By getDefaultTypeLocator(String value) {
            return By.identifier(value);
        }
    }

