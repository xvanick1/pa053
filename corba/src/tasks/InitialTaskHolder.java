package tasks;

/**
* tasks/InitialTaskHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corba.idl
* Sobota, 2021, apríla 3 17:18:02 CEST
*/

public final class InitialTaskHolder implements org.omg.CORBA.portable.Streamable
{
  public tasks.InitialTask value = null;

  public InitialTaskHolder ()
  {
  }

  public InitialTaskHolder (tasks.InitialTask initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = tasks.InitialTaskHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    tasks.InitialTaskHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return tasks.InitialTaskHelper.type ();
  }

}
