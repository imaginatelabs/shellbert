package com.imaginatelabs.shellbert;

import org.codehaus.plexus.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.util.regex.Pattern;

/**
 * Encapsulates exit code, out and err from a run command line.
 * Provides helper methods for searching the out and err streams.
 */
public class Std {

    private final ByteArrayOutputStream out;
    private final ByteArrayOutputStream err;
    private int exitCode;

    public Std(int exitCode, ByteArrayOutputStream out, ByteArrayOutputStream err) {
        this.exitCode = exitCode;
        this.out = out;
        this.err = err;
    }

    /**
     * Exit code of the command line run.
     * @return 0 - success.
     *
     *         !0 - failure.
     */
    public int getExitCode() {
        return exitCode;
    }

    public ByteArrayOutputStream out() {
        return out;
    }

    public ByteArrayOutputStream err() {
        return err;
    }

    public String outToString(){
        return out.toString().trim();
    }

    public String errToString(){
        return err.toString().trim();
    }

    public String toString(){
        return (out.toString().trim() +"\n" + err.toString().trim()).trim();
    }

    /**
     * Match a RegEx to the out by wrapping
     * java.util.regex.Pattern.compile(...).matcher(...).find().
     * @param pattern
     *         the expression to be compiled.
     * @param  flags
     *         From java.util.regex.Pattern
     *         Match flags, a bit mask that may include
     *         CASE_INSENSITIVE, MULTILINE, DOTALL,
     *         UNICODE_CASE, CANON_EQ, UNIX_LINES,
     *         LITERAL,UNICODE_CHARACTER_CLASS
     *         and COMMENTS.
     * @return True - if there are one or more matches in out.
     *
     *         False - if there are no matches in out.
     */
    public boolean outMatches(String pattern, int flags){
        return Pattern.compile(pattern, flags).matcher(out.toString()).find();
    }

    /**
     * Match a RegEx to the out by wrapping
     * outMatches and passed flag DOTALL so that
     * all characters including new lines can be matched.
     * @param pattern
     *        the expression to be compiled.
     * @return True - if there are one or more matches in out.
     *
     *         False - if there are no matches in out.
     */
    public boolean outMatches(String pattern){
        return outMatches(pattern, Pattern.DOTALL);
    }

    /**
     * Search for a string in the out.
     * @param searchString
     *          a non RegEx search string.
     * @return True - if there contains one or more searchString in out.
     *
     *         False - if there is no searchString in out.
     */
    public boolean outContains(String searchString) {
        return StringUtils.contains(out.toString(), searchString);
    }

    /**
     * Match a RegEx to the err by wrapping
     * java.util.regex.Pattern.compile(...).matcher(...).find().
     * @param pattern
     *         the expression to be compiled.
     * @param  flags
     *         From java.util.regex.Pattern
     *         Match flags, a bit mask that may include
     *         CASE_INSENSITIVE, MULTILINE, DOTALL,
     *         UNICODE_CASE, CANON_EQ, UNIX_LINES,
     *         LITERAL,UNICODE_CHARACTER_CLASS
     *         and COMMENTS.
     * @return True - if there are one or more matches in err
     *
     *         False - if there are no matches in err.
     */
    public boolean errMatches(String pattern, int flags){
        return Pattern.compile(pattern, flags).matcher(err.toString()).find();
    }

    /**
     * Match a RegEx to the err by wrapping
     * errMatches and passed flag DOTALL so that
     * all characters including new lines can be matched.
     * @param pattern
     *        the expression to be compiled.
     * @return True - if there are one or more matches in err.
     *
     *         False -  if there are no matches in err.
     */
    public boolean errMatches(String pattern){
        return errMatches(pattern, Pattern.DOTALL);
    }

    /**
     * Search for a string in the err.
     * @param searchString
     *          a non RegEx search string.
     * @return True - if there contains one or more searchString in err.
     *
     *         False - if there is no searchString in err.
     */
    public boolean errContains(String searchString) {
        return StringUtils.contains(err.toString(), searchString);
    }

    /**
     * Match a RegEx to the out and err by wrapping
     * java.util.regex.Pattern.compile(...).matcher(...).find().
     * @param pattern
     *         the expression to be compiled.
     * @param  flags
     *         From java.util.regex.Pattern
     *         Match flags, a bit mask that may include
     *         CASE_INSENSITIVE, MULTILINE, DOTALL,
     *         UNICODE_CASE, CANON_EQ, UNIX_LINES,
     *         LITERAL,UNICODE_CHARACTER_CLASS
     *         and COMMENTS.
     * @return True - if there are one or more matches in out and err.
     *
     *         False - if there are no matches in out and err.
     */
    public boolean stdMatches(String pattern, int flags){
        return outMatches(pattern, flags) || errMatches(pattern, flags);
    }

    /**
     * Match a RegEx to the out and err by wrapping
     * stdMatches and passed flag DOTALL so that
     * all characters including new lines can be matched.
     * @param pattern
     *        the expression to be compiled.
     * @return True - if there are one or more matches in out and err.
     *
     *         False - if there are no matches in out and err.
     */
    public boolean stdMatches(String pattern){
        return outMatches(pattern) || errMatches(pattern);
    }

    /**
     * Search for a string in the out and err.
     * @param searchString
     *          a non RegEx search string.
     * @return True - if there contains one or more searchString in out and err.
     *
     *         False - if there is no searchString in out and err.
     */
    public boolean contains(String searchString) {
        return outContains(searchString) || errContains(searchString);
    }
}
