function startQuiz(quizType){
    alert(`Starting ${quizType.charAt(0).toUpperCase() + quizType.slice(1)} Quiz!`);
    localStorage.setItem('category',quizType);
    window.open(`quiz.html`,"_blank");
}

function logOut(){
    localStorage.removeItem("user");
    localStorage.removeItem("pass");
    window.location.replace("login.html");
}