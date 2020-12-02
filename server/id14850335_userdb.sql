-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th12 02, 2020 lúc 08:35 AM
-- Phiên bản máy phục vụ: 10.3.16-MariaDB
-- Phiên bản PHP: 7.3.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `id14850335_userdb`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `idcategory` int(11) NOT NULL,
  `namecategory` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`idcategory`, `namecategory`) VALUES
(1, 'Nhạc trẻ'),
(2, 'Nhạc trữ tình'),
(4, 'Rap Việt'),
(5, 'Nhạc cách mạng');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `questions`
--

CREATE TABLE `questions` (
  `idquestion` int(11) NOT NULL,
  `content` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `level` int(11) NOT NULL,
  `category` int(3) NOT NULL,
  `scorepass` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `questions`
--

INSERT INTO `questions` (`idquestion`, `content`, `level`, `category`, `scorepass`) VALUES
(1, 'Xin chào các bạn ', 1, 1, 50),
(2, 'Anh tên là gì', 2, 1, 50),
(3, 'Tôi tên là Hoàng Minh', 3, 1, 50),
(4, 'Còn anh tên gì vậy', 4, 1, 50),
(6, 'Giới thiệu với Hùng đây là anh Vũ', 1, 0, 50),
(7, 'Rất hân hạnh được gặp anh Hùng', 2, 0, 50),
(8, 'Rất hân hạnh được làm quen với anh Vũ', 3, 0, 50),
(9, 'Tôi tên là Bảo', 5, 1, 50),
(10, 'Anh là người nước nào', 6, 1, 50),
(11, 'Hiện tại anh đang làm gì vậy', 4, 0, 50),
(12, 'Hiện tại tôi đang làm bác sĩ ', 5, 0, 50),
(15, 'Tôi là người nước Anh', 7, 1, 50);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `questiontopics`
--

CREATE TABLE `questiontopics` (
  `idquestiontopic` int(11) NOT NULL,
  `content` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `translate` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `level` int(11) NOT NULL,
  `idtopic` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `questiontopics`
--

INSERT INTO `questiontopics` (`idquestiontopic`, `content`, `translate`, `level`, `idtopic`) VALUES
(1, 'Xin chào', 'Hello', 1, 1),
(2, '\r\nXin chào anh', 'Hello', 2, 1),
(3, '\r\nChào em', 'Hello', 3, 1),
(4, '\r\nChào bạn', 'Hello', 4, 1),
(5, '\r\nChào thầy giáo', 'Hello teacher', 5, 1),
(6, '\r\nChào Mai ', 'Hi Mai', 6, 1),
(7, '\r\nChào tất cả mọi người', 'Hello everyone', 7, 1),
(8, '\r\nBạn có khoẻ không?', 'How are you?', 8, 1),
(9, '\r\nRất vui được gặp bạn', 'Nice to meet you', 9, 1),
(10, '\r\nRất hận hạnh được gặp bạn', 'Nice to meet you', 10, 1),
(11, '\r\nBạn tên là gì?', 'What is your name?', 1, 2),
(12, '\r\nTôi tên là Hoa', 'My name is Hoa', 2, 2),
(13, '\r\nTên của bạn là gì?', 'What is your name?', 3, 2),
(14, '\r\nTên mình là Hoàng', 'My name is Hoàng', 4, 2),
(15, '\r\nTên đầy đủ của bạn là gì?', 'What is your full name?', 5, 2),
(16, '\r\nTên đầy đủ của tôi là Dương Dương ', 'My full name is Duong Duong', 6, 2),
(17, '\r\nCho tôi xin tên của bạn', 'Give me your name', 7, 2),
(18, '\r\nBạn có khoẻ không?', 'My name is Hoa', 8, 2),
(19, '\r\nRất vui được gặp bạn', 'I am Xuan and you?', 9, 2),
(20, '\r\nTên của bạn là Tiến phải không?', 'Are you Tiến?', 10, 2),
(21, '\r\nÔng nội', 'Grandfather', 1, 3),
(22, '\r\nBà', 'Grandmother', 2, 3),
(23, '\r\nBố', 'Father', 3, 3),
(24, '\r\nMẹ', 'Mother', 4, 3),
(25, '\r\nDì', 'Aunt', 5, 3),
(26, '\r\nChú', 'Uncle', 6, 3),
(27, '\r\nBác', 'Uncle', 7, 3),
(28, '\r\nAnh trai', 'Brother', 8, 3),
(29, '\r\nChị gái', 'Sister', 9, 3),
(30, '\r\nAnh họ', 'Cousin', 10, 3),
(31, '\r\nBạn tới từ đâu?', 'Where are you from?', 1, 4),
(32, '\r\nTôi tới từ Mỹ', 'I am from the US', 2, 4),
(33, '\r\nAnh tới từ nước nào?', 'Which country are you from?', 3, 4),
(34, '\r\nTôi đến từ Anh', 'I am from England', 4, 4),
(35, '\r\nBạn là người nước ngoài à?', 'Are you a foreigner?', 5, 4),
(36, '\r\nTôi là người nước ngoài', 'I am foreigner', 6, 4),
(37, '\r\nTôi đến từ Việt Nam còn bạn?', 'I am from Vietnam and you?', 7, 4),
(38, '\r\nTôi đến từ Hàn Quốc', 'I am from Korea', 8, 4),
(39, '\r\nBạn sinh ra ở đâu?', 'Where were you born?', 9, 4),
(40, '\r\nTôi sinh ra và lớn lên ở Trung Quốc', 'I was born and raised in China', 10, 4),
(41, 'Giới thiệu với anh đây là anh Nam', 'Introduced to him this is Mr. Nam', 1, 5),
(42, '\r\nRất vui được làm quen với anh', 'Nice to meet you', 2, 5),
(43, '\r\nAnh Nam trước đây làm gì?', 'What did Mr. Nam do before?', 3, 5),
(44, '\r\nAnh Nam trước đây làm bác sĩ', 'Mr. Nam used to be a doctor', 4, 5),
(45, '\r\nTrước đây tôi cũng làm bác sĩ', 'Previously I also worked as a doctor', 5, 5),
(46, '\r\nAnh làm bác sĩ được bao lâu?', 'How long have you been a doctor?', 6, 5),
(47, '\r\nTôi là bác sĩ tám  năm', 'I am a doctor for eight years', 7, 5),
(48, '\r\nHiện tại anh đang làm gì?', 'What are you doing now?', 8, 5),
(49, '\r\nTôi đang làm kỹ sư công nghệ', 'I am a technology engineer', 9, 5),
(50, '\r\nHẹn gặp lại anh vào ngày sớm nhất', 'See you soon', 10, 5),
(51, '\r\nThời tiết chỗ bạn thế nào?', 'What is the weather like?', 1, 6),
(52, '\r\nHôm nay trời không đẹp lắm', 't is  not a very nice day', 2, 6),
(53, '\r\nHôm nay thời tiết đẹp quá', 'What a nice day', 3, 6),
(54, '\r\nTrời bắt đầu mưa rồi', 'It is starting to rain', 4, 6),
(55, '\r\nTrời nóng như thiêu', 'It is baking hot', 5, 6),
(56, '\r\nTrời không gợn bóng mây', 'There is not a cloud in the sky', 6, 6),
(57, '\r\nTrời u ám', 'The sky is overcast', 7, 6),
(58, '\r\nCậu nghĩ bây giờ đang bao nhiêu độ?', 'What temperature do you think it is?', 8, 6),
(59, '\r\nKhoảng hai mươi độ', 'Probably about 20°C', 9, 6),
(60, '\r\nTối nay trời sẽ rất lạnh', ' It is going to freeze', 10, 6),
(61, '\r\nCho tôi hỏi cổng vào sân bay Nội Bài', 'Can I ask the entrance to Noi Bai airport?', 1, 7),
(62, '\r\nVui lòng cho tôi xem hộ chiếu', 'Please show me my passport', 2, 7),
(63, '\r\nCho tôi xem vé anh đã đặt', 'Show me the ticket you ordered', 3, 7),
(64, '\r\nAnh có vé điện tử không?', 'Do you have an e ticket?', 4, 7),
(65, '\r\nTôi không có vé điện tử', 'I do not have an e ticket', 5, 7),
(66, '\r\nAnh cần ký gửi hành lý không?', 'Do you need to check in your baggage?', 6, 7),
(67, '\r\nTôi cần ký gửi hành lý', 'I need to check in baggage', 7, 7),
(68, '\r\nTôi xách tay hành lý', 'I carry the luggage', 8, 7),
(69, '\r\nChuyến bay có bị hoãn không?', 'Is the flight delayed?', 9, 7),
(70, '\r\nChuyến bay bị hoãn ba mươi phút', 'The flight is delayed 30 minutes', 10, 7),
(71, 'Đây có phải khách sạn Mường Thanh không?', 'Is this Muong Thanh hotel?', 1, 8),
(72, '\r\nCho tôi hỏi chỗ đặt phòng', 'Can I ask for a reservation?', 2, 8),
(73, '\r\nCho tôi một đặt phòng ', 'Give me a reservation', 3, 8),
(74, '\r\nAnh cần đặt phòng vào ngày nào?', 'What date do you need a reservation?', 4, 8),
(75, '\r\nTôi đặt phòng hôm nay', 'I book a room today', 5, 8),
(76, '\r\nGiá phòng một ngày bao nhiêu tiền?', 'How much is the room rate per day?', 6, 8),
(77, '\r\nGiá phòng một triệu một ngày', 'Room price one million a day', 7, 8),
(78, '\r\nCho tôi thanh toán', 'I paid for', 8, 8),
(79, '\r\nChìa khoá phòng của anh đây', 'Here is the key to my room', 9, 8),
(80, '\r\nRất vui được đón tiếp anh', 'Nice to welcome you', 10, 8),
(81, '\r\nCho tôi gọi đồ', 'I want to order', 1, 9),
(82, '\r\nTôi đang đợi bạn đến', 'I am waiting my friend', 2, 9),
(83, '\r\nAnh chị dùng gì?', 'What would you drink?', 3, 9),
(84, '\r\nTôi uống coca', 'I drink coca', 4, 9),
(85, '\r\nTôi uống nước cam', 'I drink orange juice', 5, 9),
(86, '\r\nAnh có muốn thêm đồ ăn nhẹ gì không?', 'Would you like more snacks?', 6, 9),
(87, '\r\nCho tôi một bánh bông lan', 'Give me a cake', 7, 9),
(88, '\r\nTôi ăn một bánh kem', 'I eat a cake', 8, 9),
(89, '\r\nBao lâu sẽ xong?', 'How long will it take?', 9, 9),
(90, '\r\nĐồ của anh chị tới sau năm phút', 'Your stuff comes after five minutes', 10, 9),
(91, '\r\nChiếc mũ', 'Hat', 1, 10),
(92, '\r\nCho tôi xem cái mũ kia', 'Show me that hat', 2, 10),
(93, '\r\nChiếc váy hoa', 'The floral dress', 3, 10),
(94, '\r\nTôi muốn xem chiếc váy hoa này', 'I want to see this floral dress', 4, 10),
(95, '\r\nCái mũ này bao nhiêu tiền', 'How much is this hat', 5, 10),
(96, '\r\nGiá cái mũ là một trăm nghìn đồng', 'The price of the hat is one hundred thousand dong', 6, 10),
(97, '\r\nGiá của chiếc váy bao nhiêu?', 'How much is the dress?', 7, 10),
(98, '\r\nChiếc váy năm trăm nghìn', 'It cost hundred thousand ', 8, 10),
(99, '\r\nCó váy cỡ rộng hơn không?', 'Is there a wider size skirt?', 9, 10),
(100, '\r\nCho tôi lấy váy và mũ', 'Let me get my skirt and hat', 10, 10),
(101, '\r\nXin lỗi cho tôi hỏi đường', 'Excuse me for directions', 1, 11),
(102, '\r\nAnh cần hỏi đường tới đâu', 'You need to ask where to go', 2, 11),
(103, '\r\nTôi muốn tới lăng Hồ Chí Minh', 'I want to go to Ho Chi Minh mausoleum', 3, 11),
(104, '\r\nTới đó mất bao lâu?', 'How long will it take to get there?', 4, 11),
(105, '\r\nTừ đây tới lăng Hồ Chí Minh hết mười phút', 'Ten minutes from here to Ho Chi Minh mausoleum', 5, 11),
(106, '\r\nĐường đi như thế nào?', 'How is the road?', 6, 11),
(107, '\r\nĐi thẳng', 'Go straight', 7, 11),
(108, '\r\nSau đó rẽ trái', 'Then turn left', 8, 11),
(109, '\r\nCảm ơn anh đã chỉ đường', 'Thank you for the directions', 9, 11),
(110, '\r\nKhông có gì', 'Nothing', 10, 11),
(111, '\r\nBạn có sở thích gì?', 'What are your hobbies?', 1, 12),
(112, '\r\nTôi thích chơi thể thao', 'I like play sports', 2, 12),
(113, '\r\nBạn làm gì vào thời gian rảnh?', 'How do you do in your free time?', 3, 12),
(114, '\r\nTôi thích xem phim khi rảnh', 'I like  watching movies', 4, 12),
(115, '\r\nTôi thích đá bóng khi rảnh', 'I like  play soccer', 5, 12),
(116, '\r\nSở thích của tôi là đi du lịch', 'My hobby is traveling', 6, 12),
(117, '\r\nTôi muốn đi du lịch vòng quanh thế giới', 'I want to travel around the world', 7, 12),
(118, '\r\nBạn có sở thích đọc sách không?', 'Do you enjoy reading? book', 8, 12),
(119, '\r\nTôi rất thích đọc sách', 'I really like reading books', 9, 12),
(120, '\r\nTôi hay đọc truyện hài', 'I often read comedy', 10, 12),
(121, '\r\nBạn có thích chơi thể thao không?', 'Do you like playing sports?', 1, 13),
(122, '\r\nTôi rất thích chơi thể thao', 'I really love playing sports', 2, 13),
(123, '\r\nTôi rất ghét chơi thể thao', 'I really hate playing sports', 3, 13),
(124, '\r\nTôi thích chơi bóng đá', 'I like soccer', 4, 13),
(125, '\r\nBạn có đam mê với môn thể thao gì?', 'What sport are you passionate about?', 5, 13),
(126, '\r\nMôn thể thao đam mê là bóng chuyền', 'Favorite sport is volleyball', 6, 13),
(127, '\r\nỞ Anh môn thể thao nào là phổ biến?', 'What sport is popular in the UK?', 7, 13),
(128, '\r\nNgười Anh thích chơi quần vợt', 'In England they love to play tennis', 8, 13),
(129, '\r\nỞ Việt Nam mọi người thích môn thể thao nào?', 'What sport do people like in Vietnam?', 9, 13),
(130, '\r\nViệt Nam chủ yếu thích bóng đá', 'Vietnam mainly likes football', 10, 13),
(131, '\r\nDu lịch', 'Travel', 1, 14),
(132, '\r\nTôi rất thích đi du lịch', 'I like travelling', 2, 14),
(133, '\r\nBạn đã đi du lịch được bao nhiêu nước?', 'How many countries have you traveled to?', 3, 14),
(134, '\r\nTôi đã đi năm nước trên thế giới', 'I have traveled to five countries around the world', 4, 14),
(135, '\r\nBạn tới Việt Nam được bao lâu?', 'How long have you been to Vietnam?', 5, 14),
(136, '\r\nTôi tới Việt Nam hai tuần ', 'I went to Vietnam for two weeks', 6, 14),
(137, '\r\nBạn hay đi du lịch cùng với ai?', 'Who do you travel with?', 7, 14),
(138, '\r\nTôi thích đi du lịch một mình', 'I travel alone', 8, 14),
(139, '\r\nTôi đi du lịch với gia đình', 'I travel with my family', 9, 14),
(140, '\r\nTôi sẽ quay lại du lịch Việt Nam', 'I will return to Vietnam', 10, 14),
(141, '\r\nẤn tượng Việt Nam', 'Impressive Vietnam', 1, 15),
(142, '\r\nNgười Việt rất thân thiện', 'Vietnamese people are very friendly', 2, 15),
(143, '\r\nKem Tràng Tiền', 'Trang Tien ice cream', 3, 15),
(144, '\r\nTrà đá vỉa hè', 'Ice tea on the street', 4, 15),
(145, '\r\nĐặc sản phở Hà Nội', 'Hà Nội Noodle Soup', 5, 15),
(146, '\r\nĐộc đáo chợ nổi miền Tây', 'Unique Western floating market', 6, 15),
(147, '\r\nViệt Nam có hang động lớn nhất thế giới', 'Vietnam has the largest cave in the world', 7, 15),
(148, '\r\nDu lịch Việt Nam phong phú', 'Vietnamese tourism is abundant', 8, 15),
(149, '\r\nTết nguyên đán rực rỡ đèn hoa', 'Lunar New Year with brilliant flower lights', 9, 15),
(150, '\r\nHọc tiếng Việt không hề đơn giản', 'Learning Vietnamese is not easy', 10, 15),
(201, '\r\nXin chào', 'Hello', 1, 21),
(202, '\r\nXin chào anh', 'Hello', 2, 21),
(203, '\r\nChào chị', 'Hello', 3, 21),
(204, '\r\nChào bạn', 'Hello', 4, 21),
(205, '\r\nChào anh Nam', 'Hi Mr. Nam', 5, 21),
(206, '\r\nXin chào tất cả mọi người ', 'Hello everyone', 6, 21),
(207, '\r\nCô có khoẻ không?', 'How are you?', 7, 21),
(208, '\r\nTôi rất khoẻ', 'I am very healthy', 8, 21),
(209, '\r\nChúc một ngày tuyệt vời', 'Have a great day', 9, 21),
(210, '\r\nRất vui được làm quen', 'Nice to meet you', 10, 21),
(211, '\r\nTuổi', 'Age', 1, 22),
(212, '\r\nBạn bao nhiêu tuổi?', 'How old are you?', 2, 22),
(213, '\r\nMười tám tuổi', 'Eighteen years old', 3, 22),
(214, '\r\nSáu tám tuổi', 'Six eight years old', 4, 22),
(215, '\r\nNăm nay tôi ba mươi tám tuổi', 'I am thirty-eight years old', 5, 22),
(216, '\r\nTôi hai mươi tuổi còn bạn?', 'I am twenty years old and you?', 6, 22),
(217, '\r\nTôi ba mươi tuổi', 'I am thirty years old', 7, 22),
(218, '\r\nSinh nhật bạn vào ngày nào?', 'What is your birthday?', 8, 22),
(219, '\r\nSinh nhật tên ngày mười tám ', 'My birthday is eighteen', 9, 22),
(220, '\r\nRất hận hạnh được gặp bạn', 'Eight months before my next birthday', 10, 22);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `topics`
--

CREATE TABLE `topics` (
  `idtopic` int(3) NOT NULL,
  `numbertopic` int(11) NOT NULL,
  `titletopic` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `category` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `topics`
--

INSERT INTO `topics` (`idtopic`, `numbertopic`, `titletopic`, `category`) VALUES
(1, 1, 'Chào hỏi', 0),
(2, 2, 'Hỏi tên', 0),
(3, 3, 'Đại từ nhân xưng cơ bản', 0),
(4, 4, 'Hỏi quốc tịch', 0),
(5, 5, 'Giới thiệu nghề nghiệp', 0),
(6, 6, 'Thời tiết', 0),
(7, 7, 'Ở sân bay', 0),
(8, 8, 'Ở khách sạn', 0),
(9, 9, 'Quán cafe', 0),
(10, 10, 'Mua sắm', 0),
(11, 11, 'Hỏi đường', 0),
(12, 12, 'Sở thích', 0),
(13, 13, 'Thể thao', 0),
(14, 14, 'Du lịch', 0),
(15, 15, 'Ấn tượng Việt Nam', 0),
(21, 1, 'Chào hỏi đơn giản', 1),
(22, 2, 'Hỏi tuổi', 1),
(23, 3, 'Thanh điệu', 1),
(24, 4, 'Từ đơn từ ghép từ láy', 1),
(25, 5, 'Ngày giờ', 1),
(26, 6, 'Ở bến xe', 1),
(27, 7, 'Ở bưu điện', 1),
(28, 8, 'Giới thiệu gia đình', 1),
(29, 9, 'Gặp gỡ', 1),
(30, 10, 'Tiền', 1),
(31, 11, 'Giải trí', 1),
(32, 12, 'Trường học', 1),
(33, 13, 'Cảm xúc', 1),
(34, 14, 'Nhà của anh rất đẹp', 1),
(35, 15, 'Tạm biệt chúc sức khoẻ', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tracks`
--

CREATE TABLE `tracks` (
  `id` int(11) NOT NULL,
  `title` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `author` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `link` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `idcategory` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tracks`
--

INSERT INTO `tracks` (`id`, `title`, `image`, `author`, `link`, `idcategory`) VALUES
(1, 'Cứ Ngỡ Là Anh', 'https://tienvu1305.000webhostapp.com/image/cungolaanh.jpg', 'Đinh Tùng Huy', 'https://tienvu1305.000webhostapp.com/mp3/cungolaanh.mp3', 1),
(3, 'Em Không Sai Chúng Ta Sai', 'https://tienvu1305.000webhostapp.com/image/emkhongsaichungtasai.jpg', 'Erik', 'https://tienvu1305.000webhostapp.com/mp3/emkhongsaichungtasai.mp3', 1),
(5, 'Em Gì Ơi', 'https://tienvu1305.000webhostapp.com/image/emgioi.jpg', 'K-ICM x JACK', 'https://tienvu1305.000webhostapp.com/mp3/emgioi.mp3', 1),
(7, 'Big City Boi', 'https://tienvu1305.000webhostapp.com/image/bigcityboi.jpg', 'TOULIVER x BINZ', 'https://tienvu1305.000webhostapp.com/mp3/bigcityboi.mp3', 1),
(8, 'Hoa Hải Đường', 'https://tienvu1305.000webhostapp.com/image/hoahaiduong.jpg', 'JACK', 'https://tienvu1305.000webhostapp.com/mp3/hoahaiduong.mp3', 1),
(10, 'Bỏ Lỡ Một Người', 'https://tienvu1305.000webhostapp.com/image/bolomotnguoi.jpg', 'Lê Bảo Bình', 'https://tienvu1305.000webhostapp.com/mp3/bolomotnguoi.mp3', 1),
(12, 'Bông Hoa Đẹp Nhất', 'https://tienvu1305.000webhostapp.com/image/bonghoadepnhat.jpg', 'Quân A.P', 'https://tienvu1305.000webhostapp.com/mp3/bonghoadepnhat.mp3', 1),
(16, 'Biển Ru Niềm Nhớ', 'https://tienvu1305.000webhostapp.com/image/bienruniemnho.jpg', 'Dương Triệu Vũ', 'https://tienvu1305.000webhostapp.com/mp3/bienruniemnho.mp3', 2),
(17, 'Thương Lắm Miền Trung Ơi', 'https://tienvu1305.000webhostapp.com/image/thuonglammientrungoi.jpg', 'Lê Cường', 'https://tienvu1305.000webhostapp.com/mp3/thuonglammientrungoi.mp3', 2),
(18, 'Buồn Làm Chi Em Ơi', 'https://tienvu1305.000webhostapp.com/image/buonlamchiemoi.jpg', 'Như Quỳnh', 'https://tienvu1305.000webhostapp.com/mp3/buonlamchiemoi.mp3', 2),
(19, 'Thương Quá Việt Nam', 'https://tienvu1305.000webhostapp.com/image/thuongquavietnam.jpg', 'Đan Trường', 'https://tienvu1305.000webhostapp.com/mp3/thuongquavietnam.mp3', 2),
(20, 'Bài Ca May Áo', 'https://tienvu1305.000webhostapp.com/image/baicamayao.jpg', 'Thanh Huyền', 'https://tienvu1305.000webhostapp.com/mp3/baicamayao.mp3', 5),
(21, 'Hành Khúc Lữ Đoàn 185', 'https://tienvu1305.000webhostapp.com/image/hanhkhucludoan185.jpg', 'V.A', 'https://tienvu1305.000webhostapp.com/mp3/hanhkhucludoan185.mp3', 5),
(22, 'Người Là Niềm Tin Tất Thắng', 'https://tienvu1305.000webhostapp.com/image/nguoilaniemtintatthang.jpg', 'Đình Phong', 'https://tienvu1305.000webhostapp.com/mp3/nguoilaniemtintatthang.mp3', 5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `iduser` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `imageuser` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `speakinglevel` int(11) NOT NULL,
  `listeninglevel` int(11) NOT NULL,
  `checkadmin` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`iduser`, `name`, `username`, `password`, `imageuser`, `speakinglevel`, `listeninglevel`, `checkadmin`) VALUES
(16, 'VŨ VĂN TIẾN', 'tienvu', '1234567', 'https://tienvu1305.000webhostapp.com/image_user/tienvu.jpg', 6, 8, 1),
(21, 'VŨ BÁ CÔNG', 'vu', 'vu', 'https://tienvu1305.000webhostapp.com/image_user/vu.jpg', 2, 2, 0),
(22, 'ĐÀO VĂN ANH', 't', 't', 'https://tienvu1305.000webhostapp.com/image_user/t.jpg', 4, 7, 0),
(23, 'NGUYỄN THỊ HƯỜNG', 'q', 'q', 'https://tienvu1305.000webhostapp.com/image_user/q.jpg', 6, 6, 0),
(25, 'PHẠM THỊ KIM LAN', 'e', '1234567', 'https://tienvu1305.000webhostapp.com/image_user/e.jpg', 7, 9, 0),
(26, 'LÊ PHƯƠNG ANH', 'trang', 'trang', 'https://tienvu1305.000webhostapp.com/image_user/trang.jpg', 3, 7, 0),
(28, 'NGUYỄN THỊ YẾN LINH', 'ee', 'ee', 'https://tienvu1305.000webhostapp.com/image_user/ee.jpg', 6, 8, 0),
(29, 'ABC', 'abc', 'bac', 'https://tienvu1305.000webhostapp.com/image_user/abc.jpg', 1, 1, 0),
(30, 'HOANG TRANG', 'Trabg2808', '123456aB@', 'https://tienvu1305.000webhostapp.com/image_user/Trabg2808.jpg', 1, 1, 0),
(31, 'TRANG', 'Trang2808', '123456', 'https://tienvu1305.000webhostapp.com/image_user/Trang2808.jpg', 8, 3, 0),
(36, 'PO', 'poi', '1234567', 'https://tienvu1305.000webhostapp.com/image_user/poi.jpg', 1, 5, 0),
(37, 'TIEN VU', 'pp', '1234567', 'https://tienvu1305.000webhostapp.com/image_user/pp.jpg', 1, 1, 0),
(38, 'HOÀNG', 'tr', '1234567', 'https://tienvu1305.000webhostapp.com/image_user/tr.jpg', 4, 5, 0),
(39, 'VŨ', 'tienvh', 'tienvh1', 'https://tienvu1305.000webhostapp.com/image_user/tienvh.jpg', 3, 3, 0),
(40, 'TIEN', 'tienvu123', '123456789', 'https://tienvu1305.000webhostapp.com/image_user/tienvu123.jpg', 1, 1, 0),
(41, 'ALAZA', 'alaza', '12345678', 'https://tienvu1305.000webhostapp.com/image_user/alaza.jpg', 1, 1, 0),
(42, 'TRANG', 'Tranght', '1234567', 'https://tienvu1305.000webhostapp.com/image_user/Tranght.jpg', 0, 0, 0),
(43, 'HOA', 'Hoa', 'q2345678', 'https://tienvu1305.000webhostapp.com/image_user/Hoa.jpg', 1, 1, 0),
(44, 'LAN', 'Lan', '1234567', 'https://tienvu1305.000webhostapp.com/image_user/Lan.jpg', 1, 1, 0),
(45, 'TRANGHOANGTHI', 'Tranghoang', '1234567', 'https://tienvu1305.000webhostapp.com/image_user/Tranghoang.jpg', 1, 1, 0),
(46, 'TRANGTH', 'TrangThi', '123456789', 'https://tienvu1305.000webhostapp.com/image_user/TrangThi.jpg', 1, 1, 0),
(47, 'TRANG', 'tranght62@wru.vn', '1234567890', 'https://tienvu1305.000webhostapp.com/image_user/tranght62@wru.vn.jpg', 1, 8, 0),
(48, 'TIẾN', 'ti', '1234567', 'https://tienvu1305.000webhostapp.com/image_user/ti.jpg', 1, 1, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users_questions`
--

CREATE TABLE `users_questions` (
  `id` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `idquestion` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `time` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users_questions`
--

INSERT INTO `users_questions` (`id`, `iduser`, `idquestion`, `score`, `time`) VALUES
(19, 16, 1, 100, 1605586834526),
(20, 16, 6, 62, 1605586948148),
(21, 16, 8, 88, 1605586999755),
(22, 16, 8, 66, 1605587070409),
(23, 16, 1, 75, 1605588765374),
(24, 16, 3, 60, 1605589242073),
(25, 16, 2, 75, 1605589256688),
(26, 16, 1, 50, 1605589265276),
(27, 16, 7, 71, 1605667881090),
(28, 16, 11, 57, 1605667907100),
(29, 16, 1, 50, 1605667944450),
(30, 16, 2, 100, 1605667972461),
(31, 16, 1, 100, 1605676970179),
(32, 16, 1, 50, 1605676975354),
(33, 16, 1, 50, 1605676990992),
(34, 16, 1, 50, 1605677076423),
(35, 16, 1, 75, 1605677086444),
(36, 16, 1, 50, 1605680426467),
(37, 16, 1, 50, 1605680431486),
(38, 16, 1, 50, 1605680436088),
(39, 16, 1, 50, 1605680443290),
(40, 16, 1, 50, 1605680447710),
(41, 16, 1, 50, 1605680453110),
(42, 16, 1, 50, 1605680458596),
(43, 16, 1, 50, 1605680463131),
(44, 16, 1, 50, 1605680468867),
(45, 16, 1, 50, 1605680474402),
(46, 16, 1, 50, 1605680480888),
(47, 16, 1, 50, 1605680508781),
(48, 16, 1, 50, 1605680512932),
(49, 16, 1, 50, 1605680517418),
(50, 16, 1, 50, 1605680523254),
(51, 16, 1, 50, 1605680529290),
(52, 16, 1, 50, 1605680536842),
(53, 16, 3, 60, 1605680632478),
(54, 16, 3, 60, 1605680644848),
(55, 16, 3, 60, 1605680653519),
(56, 16, 12, 57, 1605680895757),
(57, 16, 12, 57, 1605680903843),
(58, 22, 1, 75, 1605760106039),
(59, 22, 2, 75, 1605760121627),
(60, 22, 3, 80, 1605760134216),
(61, 22, 1, 100, 1605760161844),
(62, 16, 1, 100, 1606033435019),
(63, 16, 6, 100, 1606139339474),
(64, 16, 7, 57, 1606139374646),
(65, 16, 1, 100, 1606139449979),
(66, 16, 2, 75, 1606139485927),
(67, 16, 3, 50, 1606139519990),
(68, 16, 6, 62, 1606139552543),
(69, 42, 6, 87, 1606139711742),
(70, 42, 7, 70, 1606139729660),
(71, 42, 8, 66, 1606139779619),
(72, 42, 11, 85, 1606139796579),
(73, 42, 12, 100, 1606139806808),
(74, 42, 1, 100, 1606139924357),
(75, 16, 2, 100, 1606140767809),
(76, 16, 1, 70, 1606140949844),
(77, 16, 1, 100, 1606146126387),
(78, 16, 1, 95, 1606316331922),
(79, 16, 1, 70, 1606316354435),
(80, 16, 1, 90, 1606316369985),
(81, 16, 6, 82, 1606316451861),
(82, 47, 1, 100, 1606622613790),
(83, 47, 2, 95, 1606622645553),
(84, 47, 3, 100, 1606622676612),
(85, 47, 4, 90, 1606622697905),
(86, 47, 9, 75, 1606622728490),
(87, 47, 10, 80, 1606622753423),
(88, 47, 15, 60, 1606622786431),
(91, 22, 1, 50, 1606839250984),
(92, 22, 4, 60, 1606839350242),
(93, 22, 4, 80, 1606839397023),
(94, 22, 9, 100, 1606839529691),
(95, 22, 9, 50, 1606839543327),
(96, 22, 9, 50, 1606839689401),
(97, 22, 6, 62, 1606839976901),
(98, 22, 7, 85, 1606840148216),
(99, 22, 8, 77, 1606840160419),
(100, 22, 1, 50, 1606840665696),
(101, 22, 1, 50, 1606840674449),
(102, 22, 9, 100, 1606840691223),
(103, 22, 10, 60, 1606840710186),
(104, 22, 10, 80, 1606840728853),
(105, 22, 1, 100, 1606841130219),
(106, 22, 1, 75, 1606841148413),
(107, 16, 1, 75, 1606876860795),
(108, 16, 1, 100, 1606877010471),
(109, 16, 2, 100, 1606877061556),
(110, 16, 4, 80, 1606896446114),
(111, 16, 4, 60, 1606896477935),
(112, 16, 4, 80, 1606896505478),
(113, 16, 9, 75, 1606896550311),
(114, 16, 10, 60, 1606896598381),
(115, 16, 15, 80, 1606896631391),
(116, 16, 15, 100, 1606897472559),
(117, 16, 15, 100, 1606897562140);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`idcategory`);

--
-- Chỉ mục cho bảng `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`idquestion`),
  ADD UNIQUE KEY `unique` (`level`,`category`);

--
-- Chỉ mục cho bảng `questiontopics`
--
ALTER TABLE `questiontopics`
  ADD PRIMARY KEY (`idquestiontopic`),
  ADD UNIQUE KEY `idtopic` (`idtopic`,`level`) USING BTREE;

--
-- Chỉ mục cho bảng `topics`
--
ALTER TABLE `topics`
  ADD PRIMARY KEY (`idtopic`),
  ADD UNIQUE KEY `nametopic` (`numbertopic`,`category`) USING BTREE;

--
-- Chỉ mục cho bảng `tracks`
--
ALTER TABLE `tracks`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `title` (`title`),
  ADD KEY `idcategory` (`idcategory`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`iduser`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Chỉ mục cho bảng `users_questions`
--
ALTER TABLE `users_questions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idquestion` (`idquestion`,`iduser`) USING BTREE,
  ADD KEY `iduser` (`iduser`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `idcategory` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `questions`
--
ALTER TABLE `questions`
  MODIFY `idquestion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT cho bảng `questiontopics`
--
ALTER TABLE `questiontopics`
  MODIFY `idquestiontopic` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=221;

--
-- AUTO_INCREMENT cho bảng `topics`
--
ALTER TABLE `topics`
  MODIFY `idtopic` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT cho bảng `tracks`
--
ALTER TABLE `tracks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT cho bảng `users_questions`
--
ALTER TABLE `users_questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=118;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `questiontopics`
--
ALTER TABLE `questiontopics`
  ADD CONSTRAINT `questiontopics_ibfk_1` FOREIGN KEY (`idtopic`) REFERENCES `topics` (`idtopic`);

--
-- Các ràng buộc cho bảng `tracks`
--
ALTER TABLE `tracks`
  ADD CONSTRAINT `tracks_ibfk_1` FOREIGN KEY (`idcategory`) REFERENCES `categories` (`idcategory`);

--
-- Các ràng buộc cho bảng `users_questions`
--
ALTER TABLE `users_questions`
  ADD CONSTRAINT `users_questions_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `users` (`iduser`),
  ADD CONSTRAINT `users_questions_ibfk_2` FOREIGN KEY (`idquestion`) REFERENCES `questions` (`idquestion`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
