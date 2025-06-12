function handleLike(button, commentId) {
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

// Закрытие модального окна при клике вне его
window.onclick = function(event) {
    const modal = document.getElementById('createPostModal');
    if (event.target == modal) {
        closeCreatePostModal();
    }
};

/*
// Обработчик отправки формы
document.getElementById('postForm')?.addEventListener('submit', function(e) {
    e.preventDefault();
    const content = document.getElementById('postContent').value;

    // Здесь должна быть логика отправки поста на сервер
    console.log('Отправка поста:', content);

    // После успешной отправки:
    closeCreatePostModal();
    document.getElementById('postContent').value = '';

    // Можно обновить список постов или добавить новый пост в DOM
});


*/