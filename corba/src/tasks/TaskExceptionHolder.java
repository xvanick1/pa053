package tasks;

/**
* tasks/TaskExceptionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corba.idl
* Sobota, 2021, apríla 3 17:18:02 CEST
*/

public final class TaskExceptionHolder implements org.omg.CORBA.portable.Streamable
{
  public tasks.TaskException value = null;

  public TaskExceptionHolder ()
  {
  }

  public TaskExceptionHolder (tasks.TaskException initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = tasks.TaskExceptionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    tasks.TaskExceptionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return tasks.TaskExceptionHelper.type ();
  }

}