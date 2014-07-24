package com.slava.stp.by;

import com.google.common.base.Preconditions;

public abstract class By extends org.openqa.selenium.By {

    public static By dom(final String dom) {
        Preconditions.checkArgument(dom != null, "Cannot find elements with a null dom.");
        return new ByDom(dom);
    }

    public static By identifier(final String identifier) {
        Preconditions.checkArgument(identifier != null, "Cannot find elements with a null identifier.");
        return new ByIdentifier(identifier);
    }

    public static By locator(final String locator) {
        Preconditions.checkArgument(locator != null, "Cannot find elements with a null locator.");
        return new ByLocator(locator);
    }

    protected String getNoSuchElementExceptionMessage() {
        return "Cannot locate an element using " + this;
    }
}
