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
    addPostButton.style.display = 'block';

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
    addPostButton.style.display = 'none';

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

function processGeoposition(checkbox) {
    const map = document.getElementById('map');
    const geoposition = document.getElementById('geolocation');

    if (checkbox.checked) {
        map.style.display = 'block';
    } else {
        map.style.display = 'none';
        geoposition.value = '';
    }
};

function checkLikeOnPost(post) {
    const postId = document.getElementById("");''
    fetch(`/api/like/postLikeExists?postId=${postId}`, {
    method: 'POST',
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Cookie': document.cookie
    },
    body: JSON.stringify({
        postId:
    })})
}

function loadMap() {
    // Проверяем, загружена ли API Яндекс.Карт
    if (typeof ymaps === 'undefined') {
        console.error('Yandex Maps API is not loaded');
        return;
    }

    // Инициализация карты
    ymaps.ready(init);

    function init() {
        var myMap = new ymaps.Map("map", {
            center: [55.792159, 49.122014], // координаты центра карты
            zoom: 17
        });

        var myPlacemark = null; // маркер

        myMap.events.add('click', function (e) {
            var coords = e.get('coords');

            // Если маркер уже есть, удаляем его
            if (myPlacemark) {
                myMap.geoObjects.remove(myPlacemark);
            }

            // Создаем новый маркер
            myPlacemark = new ymaps.Placemark(coords, {
                hintContent: 'Выбранная точка',
                balloonContent: 'Координаты: ' + [
                    coords[0].toPrecision(6),
                    coords[1].toPrecision(6)
                ].join(', ')
            }, {
                preset: 'islands#redDotIcon'
            });

            // Добавляем маркер на карту
            myMap.geoObjects.add(myPlacemark);

            // Записываем координаты в поле формы
            var coordField = document.getElementById('geolocation');
            if (coordField) {
                coordField.value = coords[1].toFixed(6) + ',' + coords[0].toFixed(6);
            }
        });
    }
};
document.addEventListener('DOMContentLoaded', loadMap());
// Закрытие модального окна при клике вне его
window.onclick = function(event) {
    const modal = document.getElementById('createPostModal');
    if (event.target == modal) {
        closeCreatePostModal();
    }
};
