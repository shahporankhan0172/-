# মোবাইল ফোন দিয়ে APK তৈরি করার নির্দেশনা

এই প্রজেক্টে GitHub Actions workflow যোগ করা হয়েছে। এটি GitHub-এর ক্লাউড কম্পিউটারে Android APK build করার চেষ্টা করবে; নিজের কম্পিউটার লাগবে না।

## যা লাগবে
- GitHub account
- ইন্টারনেট
- Android ফোনের browser

## ধাপ
1. GitHub-এ sign in করে নতুন একটি repository তৈরি করুন। নাম দিন `technical-education-private-center`। Public বা Private—যেটি পছন্দ।
2. Repository-তে **Add file > Upload files** নির্বাচন করে ZIP ফাইলটি আগে Extract করে তার ভেতরের সব ফাইল ও `.github` ফোল্ডারসহ আপলোড করুন। মোবাইলে hidden `.github` ফোল্ডার দেখা/আপলোড করা কঠিন হতে পারে; সেটি বাদ পড়লে workflow চলবে না।
3. Upload শেষে **Commit changes** চাপুন।
4. Repository-র **Actions** tab খুলুন।
5. বাম পাশ থেকে **Build Android APK** workflow নির্বাচন করুন।
6. **Run workflow** চাপুন।
7. Build সফল হলে run-টি খুলে **Artifacts** থেকে `technical-education-private-center-debug-apk` ডাউনলোড করুন।
8. ZIP artifact Extract করে `app-debug.apk` ফাইলটি ফোনে ইনস্টল করুন। Android চাইলে browser/file manager-এর জন্য “Install unknown apps” অনুমতি দিতে হবে।

## গুরুত্বপূর্ণ
- এটি debug APK, Play Store-এর release APK নয়।
- এই প্রজেক্টটি এখনো প্রাথমিক UI prototype। লগইন, শিক্ষার্থী অনুমোদন, হাজিরা, ফলাফল, ফি, নোটিশ ও PDF-এর বাস্তব ডেটা অনলাইনে সংরক্ষণের backend এখনো যুক্ত হয়নি।
- ডেমো লগইন আসল পরিচয় যাচাই করে না। বাস্তব শিক্ষার্থীর তথ্য এতে দেবেন না।
- GitHub-এ আপলোড করার সময় `.github/workflows/android-apk.yml` ফাইলটি অবশ্যই থাকতে হবে।
- GitHub-এর মোবাইল UI-তে folder upload সম্ভব না হলে এই পদ্ধতিতে সাহায্য নিতে হবে; শুধু ZIP ফাইল repository-তে আপলোড করলে workflow নিজে থেকে source extract করবে না।
