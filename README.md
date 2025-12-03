# README.md - TBlog

<div align="center">
  <img src="https://github.com/Tahanoa/TBlog/blob/master/src/main/resources/static/Screenshot%202025-12-02%20192324.png?raw=true" width="800" alt="صفحه اصلی TBlog">
  
  <h1>TBlog - سیستم مدیریت وبلاگ</h1>
  
  <p>
    <strong>یک سیستم مدیریت محتوای وبلاگ مدرن با طراحی زیبا و امکانات کامل</strong>
  </p>
  
  <div>
    <img src="https://img.shields.io/badge/Spring%20Boot-3.1.5-green" alt="Spring Boot">
    <img src="https://img.shields.io/badge/Java-17-orange" alt="Java 17">
    <img src="https://img.shields.io/badge/Thymeleaf-3.1-blue" alt="Thymeleaf">
    <img src="https://img.shields.io/badge/TailwindCSS-3.3-38b2ac" alt="TailwindCSS">
    <img src="https://img.shields.io/badge/PostgreSQL-15-336791" alt="PostgreSQL">
  </div>
</div>

## 🎯 معرفی پروژه

**TBlog** یک سیستم مدیریت محتوای وبلاگ کامل است که با **Spring Boot** و **Thymeleaf** توسعه داده شده است. این پروژه شامل پنل مدیریت پیشرفته، سیستم کامنت‌دهی، مدیریت کاربران و دسته‌بندی‌ها می‌باشد.

## ✨ ویژگی‌ها

### 🎨 رابط کاربری مدرن
- طراحی تاریک (Dark Mode) با رنگ‌بندی بنفش
- واکنش‌گرا (Responsive) برای تمام دستگاه‌ها
- استفاده از TailwindCSS برای استایل‌دهی
- فونت فارسی Vazirmatn برای پشتیبانی کامل از زبان فارسی

### 📝 مدیریت محتوا
- ایجاد، ویرایش و حذف پست‌ها
- دسته‌بندی و برچسب‌گذاری پست‌ها
- آپلود تصاویر برای پست‌ها
- پیش‌نمایش پست‌ها قبل از انتشار

### 💬 سیستم کامنت‌دهی
- امکان کامنت گذاشتن برای کاربران
- سیستم تأیید کامنت توسط مدیر
- تفکیک کامنت‌های تأیید شده و در انتظار تأیید

### 👥 مدیریت کاربران
- ثبت‌نام و ورود کاربران
- سطوح دسترسی مختلف (ادمین، کاربر عادی)
- پنل مدیریت کاربران

### 📊 داشبورد مدیریت
<div align="center">
  <img src="https://github.com/Tahanoa/TBlog/blob/master/src/main/resources/static/Screenshot%202025-12-02%20192356.png?raw=true" width="800" alt="داشبورد مدیریت">
</div>

- آمار کلی (پست‌ها، کامنت‌ها، کاربران)
- نمودارهای آماری
- آخرین فعالیت‌ها
- وضعیت سیستم

### 🔐 امنیت
- احراز هویت با Spring Security
- CSRF Protection
- رمزنگاری رمز عبور
- محدودیت دسترسی بر اساس نقش‌ها

## 🛠 تکنولوژی‌ها

### Backend
- **Java 17** - زبان برنامه‌نویسی اصلی
- **Spring Boot 3.1.5** - فریمورک اصلی
- **Spring Security** - احراز هویت و مجوزدهی
- **Spring Data JPA** - دسترسی به دیتابیس
- **Thymeleaf** - موتور قالب‌بندی

### Frontend
- **TailwindCSS 3.3** - فریمورک CSS
- **JavaScript (ES6+)** - تعاملات سمت کلاینت
- **Font Awesome 6** - آیکون‌ها
- **Chart.js** - نمودارها

### Database
- **PostgreSQL 15** - دیتابیس اصلی
- **Liquibase** - مدیریت تغییرات دیتابیس

### Tools & Libraries
- **Lombok** - کاهش boilerplate code
- **Validation API** - اعتبارسنجی داده‌ها
- **JUnit 5** - تست‌نویسی

## 🚀 نصب و راه‌اندازی

### پیش‌نیازها
- JDK 17 یا بالاتر
- Maven 3.8 یا بالاتر
- PostgreSQL 15
- Git

### مراحل نصب

1. **کلون کردن پروژه**
```bash
git clone https://github.com/Tahanoa/TBlog.git
cd TBlog
```

2. **تنظیم دیتابیس PostgreSQL**
```sql
CREATE DATABASE tblog;
CREATE USER tblog_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE tblog TO tblog_user;
```

3. **تنظیم فایل application.properties**
```properties
# در src/main/resources/application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tblog
spring.datasource.username=tblog_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

4. **راه‌اندازی پروژه**
```bash
mvn clean install
mvn spring-boot:run
```

5. **دسترسی به برنامه**
- آدرس: http://localhost:8080
- ادمین پیش‌فرض:
  - نام کاربری: admin
  - رمز عبور: admin123

## 📁 ساختار پروژه

```
TBlog/
├── src/
│   ├── main/
│   │   ├── java/com/tblog/
│   │   │   ├── config/          # تنظیمات Spring
│   │   │   ├── controller/      # کنترلرها
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── entity/          # موجودیت‌های JPA
│   │   │   ├── repository/      # Repository interfaces
│   │   │   ├── service/         # سرویس‌های کسب‌وکار
│   │   │   └── TBlogApplication.java
│   │   └── resources/
│   │       ├── static/          # فایل‌های استاتیک
│   │       │   ├── css/
│   │       │   ├── js/
│   │       │   └── images/
│   │       ├── templates/       # صفحات Thymeleaf
│   │       │   ├── auth/        # صفحات احراز هویت
│   │       │   ├── admin/       # صفحات مدیریت
│   │       │   ├── posts/       # صفحات پست‌ها
│   │       │   └── layout/      # قالب‌های اصلی
│   │       └── application.properties
│   └── test/                    # تست‌ها
└── pom.xml
```

## 🖼 صفحه‌ها

### صفحه اصلی
<div align="center">
  <img src="https://github.com/Tahanoa/TBlog/blob/master/src/main/resources/static/Screenshot%202025-12-02%20192324.png?raw=true" width="800" alt="صفحه اصلی">
</div>

- نمایش آخرین پست‌ها
- دسته‌بندی‌ها
- سیستم جستجو
- پینشن

### جزئیات پست
<div align="center">
  <img src="https://github.com/Tahanoa/TBlog/blob/master/src/main/resources/static/Screenshot%202025-12-02%20192343.png?raw=true" width="800" alt="جزئیات پست">
</div>

- نمایش کامل محتوای پست
- سیستم کامنت‌دهی
- اطلاعات نویسنده
- تگ‌ها و دسته‌بندی

### پنل مدیریت پست‌ها
- لیست پست‌ها با قابلیت جستجو و فیلتر
- فرم ایجاد/ویرایش پست
- مدیریت وضعیت انتشار

### مدیریت کاربران
- لیست کاربران
- تغییر نقش‌ها
- ایجاد کاربر جدید

## 🔐 امنیت

### نقش‌ها (Roles)
- **ROLE_ADMIN**: دسترسی کامل به همه بخش‌ها
- **ROLE_USER**: فقط امکان کامنت گذاشتن

### محافظت‌های امنیتی
- CSRF Protection
- SQL Injection Prevention
- XSS Protection
- Secure Password Hashing (BCrypt)

## 📡 API Endpoints

### پست‌ها
```
GET    /posts                   - لیست پست‌ها
GET    /posts/{id}             - جزئیات پست
POST   /posts                  - ایجاد پست جدید
PUT    /posts/{id}             - ویرایش پست
DELETE /posts/{id}             - حذف پست
POST   /posts/{id}/view        - افزایش بازدید
```

### کامنت‌ها
```
GET    /comments/post/{postId} - کامنت‌های یک پست
POST   /comments               - ایجاد کامنت جدید
PUT    /comments/{id}/approve  - تأیید کامنت
DELETE /comments/{id}          - حذف کامنت
```

### کاربران
```
GET    /users                  - لیست کاربران
POST   /users/register         - ثبت‌نام کاربر
PUT    /users/{id}/role       - تغییر نقش کاربر
DELETE /users/{id}            - حذف کاربر
```

### احراز هویت
```
GET    /auth/login            - صفحه ورود
POST   /auth/login           - پردازش ورود
POST   /auth/register        - ثبت‌نام
POST   /auth/logout          - خروج
```

## 🧪 تست

### اجرای تست‌ها
```bash
# اجرای تمام تست‌ها
mvn test

# اجرای تست‌های یک کلاس خاص
mvn test -Dtest=PostServiceTest

# تست با گزارش پوشش کد
mvn clean test jacoco:report
```

### انواع تست
- **Unit Tests**: تست سرویس‌ها و کنترلرها
- **Integration Tests**: تست یکپارچگی با دیتابیس
- **Security Tests**: تست امنیت و احراز هویت

## 🚢 استقرار

### استقرار با Docker
```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### استقرار روی سرور
```bash
# ساخت فایل JAR
mvn clean package -DskipTests

# انتقال به سرور
scp target/tblog-*.jar user@server:/opt/tblog/

# راه‌اندازی سرویس
sudo systemctl start tblog.service
```

## 🤝 مشارکت

1. Fork پروژه
2. ایجاد Branch جدید (`git checkout -b feature/AmazingFeature`)
3. Commit تغییرات (`git commit -m 'Add some AmazingFeature'`)
4. Push به Branch (`git push origin feature/AmazingFeature`)
5. باز کردن Pull Request

## 📄 مجوز

این پروژه تحت مجوز MIT منتشر شده است. برای جزئیات بیشتر فایل [LICENSE](LICENSE) را مطالعه کنید.

##Tahanoa

---

<div align="center">
  <p>ساخته شده با ❤️   TBlog</p>
</div>
