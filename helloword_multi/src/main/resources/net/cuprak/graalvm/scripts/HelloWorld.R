helloWorld <- function(name) {
  tmp <- paste("R says Hello World ",name)
  return(tmp)
}
export("helloWorld", helloWorld)
