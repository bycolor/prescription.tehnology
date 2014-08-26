var event = document.createEvent('Event');
event.initEvent('LOGGEDIN',true,true);
document.addEventListener('LOGGEDIN',function(e){

});
document.dispatchEvent(event);