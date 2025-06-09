document.addEventListener('DOMContentLoaded', function() {
    // Переключение между вкладками
    const menuItems = document.querySelectorAll('.menu-item');
    const tabs = document.querySelectorAll('.settings-tab');

    menuItems.forEach(item => {
        item.addEventListener('click', function() {
            // Удаляем активный класс у всех элементов меню и вкладок
            menuItems.forEach(i => i.classList.remove('active'));
            tabs.forEach(tab => tab.classList.remove('active'));

            // Добавляем активный класс к выбранному элементу
            this.classList.add('active');

            // Показываем соответствующую вкладку
            const tabId = this.getAttribute('data-tab');
            document.getElementById(tabId).classList.add('active');
        });
    });

    // Предпросмотр аватарки
    const avatarInput = document.getElementById('avatar-input');
    const avatarPreview = document.getElementById('avatar-preview');

    avatarInput.addEventListener('change', function(e) {
        const file = e.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(event) {
                avatarPreview.src = event.target.result;
            };
            reader.readAsDataURL(file);
        }
    });

    // Удаление аватарки
    document.getElementById('remove-avatar').addEventListener('click', function() {
        avatarPreview.src = '/images/default-avatar.png';
        avatarInput.value = '';
    });

    // Проверка сложности пароля
    const newPassword = document.getElementById('new-password');
    const strengthBar = document.querySelector('.strength-bar');
    const strengthText = document.querySelector('.strength-text');

    newPassword.addEventListener('input', function() {
        const password = this.value;
        let strength = 0;

        // Проверка длины
        if (password.length >= 8) strength += 1;
        if (password.length >= 12) strength += 1;

        // Проверка на наличие цифр
        if (/\d/.test(password)) strength += 1;

        // Проверка на наличие спецсимволов
        if (/[!@#$%^&*(),.?":{}|<>]/.test(password)) strength += 1;

        // Проверка на наличие букв разного регистра
        if (/[a-z]/.test(password) && /[A-Z]/.test(password)) strength += 1;

        // Обновляем индикатор
        updateStrengthIndicator(strength);
    });

    function updateStrengthIndicator(strength) {
        let width = '0%';
        let color = '#f44336';
        let text = 'Слабый';

        if (strength >= 4) {
            width = '100%';
            color = '#4CAF50';
            text = 'Сильный';
        } else if (strength >= 2) {
            width = '66%';
            color = '#FFC107';
            text = 'Средний';
        } else if (strength >= 1) {
            width = '33%';
        }

        strengthBar.style.width = width;
        strengthBar.style.backgroundColor = color;
        strengthText.textContent = text;
        strengthText.style.color = color;
    }

    // Валидация форм
    document.getElementById('profile-form').addEventListener('submit', function(e) {
        e.preventDefault();
        alert('Изменения профиля сохранены!');
    });

    document.getElementById('password-form').addEventListener('submit', function(e) {
        e.preventDefault();
        const currentPass = document.getElementById('current-password').value;
        const newPass = document.getElementById('new-password').value;
        const confirmPass = document.getElementById('confirm-password').value;

        if (newPass !== confirmPass) {
            alert('Пароли не совпадают!');
            return;
        }

        if (newPass.length < 8) {
            alert('Пароль должен содержать минимум 8 символов!');
            return;
        }

        alert('Пароль успешно изменён!');
        this.reset();
    });

    document.getElementById('privacy-form').addEventListener('submit', function(e) {
        e.preventDefault();
        alert('Настройки приватности сохранены!');
    });
});