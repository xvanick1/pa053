package tasks;


/**
* tasks/SimpleTaskHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from corba.idl
* Sobota, 2021, apríla 3 17:18:02 CEST
*/

abstract public class SimpleTaskHelper
{
  private static String  _id = "IDL:tasks/SimpleTask:1.0";

  public static void insert (org.omg.CORBA.Any a, tasks.SimpleTask that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static tasks.SimpleTask extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (tasks.SimpleTaskHelper.id (), "SimpleTask");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static tasks.SimpleTask read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_SimpleTaskStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, tasks.SimpleTask value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static tasks.SimpleTask narrow (java.lang.Object obj)  {
    if (obj == null)
      return null;
    else if (obj instanceof org.omg.CORBA.Object)
      return narrow ((org.omg.CORBA.Object) obj);
    throw new org.omg.CORBA.BAD_PARAM ();
  }

  public static tasks.SimpleTask narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof tasks.SimpleTask)
      return (tasks.SimpleTask)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      tasks._SimpleTaskStub stub = new tasks._SimpleTaskStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static tasks.SimpleTask unchecked_narrow (java.lang.Object obj)  {
    if (obj == null)
      return null;
    else if (obj instanceof org.omg.CORBA.Object)
      return unchecked_narrow ((org.omg.CORBA.Object) obj);
    throw new org.omg.CORBA.BAD_PARAM ();
  }

  public static tasks.SimpleTask unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof tasks.SimpleTask)
      return (tasks.SimpleTask)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      tasks._SimpleTaskStub stub = new tasks._SimpleTaskStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
