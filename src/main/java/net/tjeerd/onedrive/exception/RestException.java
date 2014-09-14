package net.tjeerd.onedrive.exception;

public class RestException extends Exception
{
    public RestException()
    {
    }

    /**
     * REST exception message implementation.
     *
     * @param message to show
     */
    public RestException(String message)
    {
        super (message);
    }

    /**
     * REST exception.
     *
     * @param cause cause of the exception
     */
    public RestException(Throwable cause)
    {
        super (cause);
    }

    /**
     * REST exception.
     *
     * @param message message to show
     * @param cause cause of the REST exception
     */
    public RestException(String message, Throwable cause)
    {
        super (message, cause);
    }


}
