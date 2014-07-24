function spaces(n) {
    n = n * 2;
    return Array(n + 1).join(" ");
}

function line(s, n) {
    return spaces(n) + s + '\n';
}

function toComment(s, n) {
    return line('<!--' + s + '-->', n);
}

function toCDATA(s, n) {
    return line('<![CDATA[' + s + ']]>', n);
}

function toTagWithCDATA(tag, s, n) {
    return line('<' + tag + '>', n)
    + toCDATA(s, n + 1)
    + line('</'+ tag + '>', n);
}

function toCommand(c, n) {
    return line('<command name="' + c.command + '">', n)
    + toTagWithCDATA('target', c.target, n + 1)
    + toTagWithCDATA('value', c.value, n + 1)
    + line('</command>', n);
}

function toCommands(commands, n) {
    var response = '';
    for(i = 0; i < commands.length; i++) {
        if(commands[i].type == 'comment') {
            response+= toComment(commands[i].comment, n);
        } else if(commands[i].type == 'command') {
            response+= toCommand(commands[i], n);
        }
    }
    return response;
}

function toDriver(commands, n) {
    return line('<driver>', n)
    + toCommands(commands, n + 1)
    + line('</driver>', n);
}

function toTest(commands, name) {
    return line('<test' + (name ? ' name="' + name + '"' : '' ) + '>', 0)
    + toDriver(commands, 1)
    + line('</test>', 0);
}

function toTestXML(commands, name) {
    return '<?xml version="1.0" encoding="UTF-8"?>\n' + toTest(commands, name);
}

/**
 * Parse source and update TestCase. Throw an exception if any error occurs.
 *
 * @param testCase TestCase to update
 * @param source The source to parse
 */
function parse(testCase, source) {
    alert('Does not support conversion from source to testCase');
}

/**
 * Format TestCase and return the source.
 *
 * @param testCase TestCase to format
 * @param name The name of the test case, if any. It may be used to embed title into the source.
 */
function format(testCase, name) {
    return toTestXML(testCase.commands, name);
}

/**
 * Format an array of commands to the snippet of source.
 * Used to copy the source into the clipboard.
 *
 * @param The array of commands to sort.
 */
function formatCommands(commands) {
    return toTest(commands)
}

/*
 * Optional: The customizable option that can be used in format/parse functions.
 */
//options = {nameOfTheOption: 'The Default Value'}

/*
 * Optional: XUL XML String for the UI of the options dialog
 */
//configForm = '<textbox id="options_nameOfTheOption"/>'

configForm = '<description>Selenium Test Page Formatter</description>';
