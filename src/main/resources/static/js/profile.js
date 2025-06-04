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
}

function showPosts() {
    let comments = document.getElementsByClassName('comments')[0];
    comments.style.display = 'none';
    let commentsTitle = document.getElementsByClassName('comments-title')[0];
    commentsTitle.style.color = 'var(--dark-color)';

    let posts = document.getElementsByClassName('posts')[0];
    posts.style.display = 'block';
    let postsTitle = document.getElementsByClassName('posts-title')[0];
    postsTitle.style.color = 'var(--light-color)';
}

function showComments() {
    let posts = document.getElementsByClassName('posts')[0];
    posts.style.display = 'none';
    let postsTitle = document.getElementsByClassName('posts-title')[0];
    postsTitle.style.color = 'var(--dark-color)';

    let comments = document.getElementsByClassName('comments')[0];
    comments.style.display = 'block';
    let commentsTitle = document.getElementsByClassName('comments-title')[0];
    commentsTitle.style.color = 'var(--light-color)';
}