function handleLikeOnPost(button) {
    const postId = button.dataset.postId;

    fetch(`/api/like/post?postId=${postId}`, {
    method: 'POST',
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Cookie': document.cookie
    },
    body: JSON.stringify({
        postId: 2
    })})

    const likeCountElement = button.querySelector('.like-count');
    let currentCount = parseInt(likeCountElement.textContent);
    const isLiked = button.classList.contains('liked');

    if (isLiked) {
        button.classList.remove('liked');
        currentCount -= 1;
        button.querySelector('i').classList.replace('fas', 'far');
    } else {
        button.classList.add('liked');
        currentCount += 1;
        button.querySelector('i').classList.replace('far', 'fas');
    }

    likeCountElement.textContent = currentCount;
};

function handleLikeOnComment(button) {
    const commentId = button.dataset.commentId;

    fetch(`/api/like/comment?commentId=${commentId}`, {
    method: 'POST',
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Cookie': document.cookie
    },
    body: JSON.stringify({
        postId: 2
    })})

    const likeCountElement = button.querySelector('.like-count');
    let currentCount = parseInt(likeCountElement.textContent);
    const isLiked = button.classList.contains('liked');

    if (isLiked) {
        button.classList.remove('liked');
        currentCount -= 1;
        button.querySelector('i').classList.replace('fas', 'far');
    } else {
        button.classList.add('liked');
        currentCount += 1;
        button.querySelector('i').classList.replace('far', 'fas');
    }

    likeCountElement.textContent = currentCount;
};

function showPosts() {
    let comments = document.getElementsByClassName('comments')[0];
    comments.style.display = 'none';
    let commentsTitle = document.getElementsByClassName('comments-title')[0];
    commentsTitle.style.color = 'var(--dark-color)';
    let addPostButton = document.getElementsByClassName('add-post-btn')[0];
    addPostButton.style.display = 'none';

    let posts = document.getElementsByClassName('posts')[0];
    posts.style.display = 'block';
    let postsTitle = document.getElementsByClassName('posts-title')[0];
    postsTitle.style.color = 'var(--light-color)';
};

function showComments() {
    let posts = document.getElementsByClassName('posts')[0];
    posts.style.display = 'none';
    let postsTitle = document.getElementsByClassName('posts-title')[0];
    postsTitle.style.color = 'var(--dark-color)';
    let addPostButton = document.getElementsByClassName('add-post-btn')[0];
    addPostButton.style.display = 'block';

    let comments = document.getElementsByClassName('comments')[0];
    comments.style.display = 'block';
    let commentsTitle = document.getElementsByClassName('comments-title')[0];
    commentsTitle.style.color = 'var(--light-color)';
};

// Функция для открытия модального окна
function openCreatePostModal() {
    const modal = document.getElementById('createPostModal');
    const profileBlock = document.getElementsByClassName('profile-page')[0];

    modal.style.display = 'block';
    profileBlock.style.filter = 'blur(5px)';
};

// Функция для закрытия модального окна
function closeCreatePostModal() {
    const modal = document.getElementById('createPostModal');
    const profileBlock = document.getElementsByClassName('profile-page')[0];

    modal.style.display = 'none';
    profileBlock.style.filter = 'blur(0px)';
};

function processAnon(checkbox) {
    const isAnonimous = document.getElementById('isAnonimous');
    if (checkbox.checked) {
        isAnonimous.value = '1';
    } else {
        map.style.display = 'none';
        isAnonimous.value = '0';
    }
};

function processSubscribe(button) {
    const userId = button.dataset.userId;

    if (button.textContent === "Подписаться") {
        button.className = 'button button--secondary';
        button.textContent = 'Отписаться';
    } else {
        button.className = 'button button--primary';
        button.textContent = 'Подписаться';
    }


    fetch(`/api/subscribe?profileUserId=${userId}`, {
    method: 'POST',
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Cookie': document.cookie
    },
    body: JSON.stringify({
        profileUserId: userId
    })})
};

// Закрытие модального окна при клике вне его
window.onclick = function(event) {
    const modal = document.getElementById('createPostModal');
    if (event.target == modal) {
        closeCreatePostModal();
    }
};