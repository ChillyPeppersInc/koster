function previewAvatar(input) {
    const preview = document.getElementById('avatar-preview');
    const file = input.files[0];

    if (file) {
        const reader = new FileReader();

        reader.onload = function(e) {
            preview.src = e.target.result;

            preview.classList.add('animate__animated', 'animate__fadeIn');
            setTimeout(() => {
                preview.classList.remove('animate__animated', 'animate__fadeIn');
            }, 1000);
        }

        reader.readAsDataURL(file);
    }
}

// Обработчик для кнопки удаления
document.getElementById('remove-avatar').addEventListener('click', function() {
    const preview = document.getElementById('avatar-preview');
    preview.src = '/images/avatars/default-avatar.png';
    document.getElementById('avatar').value = 'delete';

    preview.classList.add('animate__animated', 'animate__fadeIn');
    setTimeout(() => {
        preview.classList.remove('animate__animated', 'animate__fadeIn');
    }, 1000);
});