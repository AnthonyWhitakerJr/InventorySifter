/**
 * Created by Anthony on 8/11/17.
 */
let a = "Hello World";
document.querySelector('.my-class').innerHTML = a;

a = "I hate this world";

changeValue = () => {
    document.querySelector(".my-class").innerHTML = a
};