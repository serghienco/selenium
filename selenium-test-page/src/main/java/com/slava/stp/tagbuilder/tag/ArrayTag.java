package com.slava.stp.tagbuilder.tag;

import com.slava.stp.tagbuilder.AbstractTag;

import java.io.Writer;

public class ArrayTag extends AbstractTag {

    @Override
    public void doBeginTag(Writer out) throws Exception {
        out.append("[");
    }

    @Override
    public void doText(Writer out, String text) throws Exception {
        out.append(text).append(",");
    }

    @Override
    public void doEndChildElement(Writer out) throws Exception {
        out.append(",");
    }

    @Override
    public void doEndTag(Writer out) throws Exception {
        out.append("]");
    }
}
