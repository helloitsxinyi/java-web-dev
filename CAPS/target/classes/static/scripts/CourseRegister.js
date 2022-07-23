function registerConfirmation(courseCode, address) {
  if (confirm("Do you wish to register for " + courseCode + "?")) {
    window.location.replace(address+"/"+courseCode);
  } 
  else {
    return;
  }
}