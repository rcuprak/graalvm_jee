def helloWorld(name)
  return "Ruby says Hello World " + name;
end

Truffle::Interop.export_method( :helloWorld)