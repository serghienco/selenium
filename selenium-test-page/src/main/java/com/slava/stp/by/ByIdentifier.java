package com.slava.stp.by;

public static class ByIdentifier extends By {

        private String identifier;

        public ByIdentifier(String identifier) {
            this.identifier = identifier;
        }

        @Override
        public List<WebElement> findElements(SearchContext context) {
            List<WebElement> findElements = By.id(identifier).findElements(context);
            if (findElements.size() == 0) {
                findElements = By.name(identifier).findElements(context);
            }
            return findElements;
        }

        @Override
        public WebElement findElement(SearchContext context) {
            try {
                return By.id(identifier).findElement(context);
            } catch (NoSuchElementException e) {
                try {
                    return By.name(identifier).findElement(context);
                } catch (NoSuchElementException e2) {
                    throw new NoSuchElementException(getNoSuchElementExceptionMessage(), e);
                }
            }
        }

        @Override
        public String toString() {
            return "By.identifier: " + identifier;
        }
    }
