let currentQuestionIndex = 0;
let questions = [];
let correctAnswers = 0;
let totalQuestions = 0;
let username = null;
let password = null;

document.title = localStorage.getItem('category') + " Quiz";
document.getElementById('heading').textContent = localStorage.getItem('category') + " Quiz";


const userData = JSON.parse(localStorage.getItem("user"));
console.log(userData)
if(userData!=null){
    username = userData.email;
    password = localStorage.getItem("pass");
}
const authHeader = btoa(`${username}:${password}`);
console.log(userData.email)
console.log(password);

async function fetchQuestions() {
    try{
        const response = await fetch('http://localhost:8080/api/ques?category=' + localStorage.getItem("category"),{
            method: 'GET',
            headers: {
                'Content-Type' : 'application/json',
                Authorization: `Basic ${authHeader}`,
            }})
        if(!response.ok){
            throw new Error('Network response was not ok');
        }
        questions = await response.json();
        totalQuestions = questions.length;
        document.getElementById('total-questions').textContent = totalQuestions;
        displayQuestion();
    } catch(error) {
        console.error('Error fetching questions:', error);
        document.getElementById('question-text').textContent = "Failed to load questions. Please try again,";
    }
}

function startCountdown(durationInSeconds) {
    let timeRemaining = durationInSeconds;
    const timerDisplay = document.getElementById('timer');
    const timerInterval = setInterval(() => {
        const minutes = Math.floor(timeRemaining / 60);
        const seconds = timeRemaining % 60;
        timerDisplay.textContent = `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
        if (timeRemaining <= 0) {
            clearInterval(timerInterval);
            timerDisplay.textContent = "Time's up!";
        }
        timeRemaining--;
    }, 1000);
}

function displayQuestion(){
    if (questions.length == 0) return;

    const questionText = document.getElementById('question-text');
    const optionsContainer = document.getElementById('options-container');
    const currentQuestion = questions[currentQuestionIndex];

    questionText.textContent = currentQuestion.questionText;
    optionsContainer.innerHTML = '';

    currentQuestion.options.forEach(option => {
        const button = document.createElement('button');
        button.textContent = option;
        button.className = 'option';
        button.onclick = () => checkAnswer(option);
        optionsContainer.appendChild(button);
    });

    document.getElementById('current-question').textContent = currentQuestionIndex+1;
}

function checkAnswer(selectedOption){
    const currentQuestion = questions[currentQuestionIndex];
    if(selectedOption === currentQuestion.correctAnswer){
        correctAnswers++;
    }

    nextQuestion();
}


function nextQuestion() {
    if(currentQuestionIndex < questions.length -1){
        currentQuestionIndex++;
        displayQuestion();
    }else{
        localStorage.setItem('quizScore', correctAnswers);
        localStorage.setItem('totalQuestions', totalQuestions);
        window.location.href = 'result.html';
    }
}

window.onload = fetchQuestions;
document.addEventListener('DOMContentLoaded', () => {
    startCountdown(60); 
});