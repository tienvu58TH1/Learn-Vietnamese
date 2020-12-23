-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th12 17, 2020 lúc 02:30 AM
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
(1, 'Xin chào', 1, 0, 50),
(2, 'Bố mẹ', 2, 0, 50),
(3, 'Xe buýt', 3, 0, 50),
(4, 'Mùa đông', 4, 0, 50),
(5, 'Áo cộc\r\n', 5, 0, 50),
(6, 'Gia đình tôi', 6, 0, 50),
(7, 'Màu xanh', 7, 0, 50),
(8, 'Đi du lịch', 8, 0, 55),
(9, 'Phở Hà Nội', 9, 0, 50),
(10, 'Đi chợ mua sắm', 10, 0, 50),
(11, 'Không có gì', 11, 0, 50),
(12, 'Chúc bạn sức khoẻ', 12, 0, 50),
(13, 'Rất vui được gặp bạn', 13, 0, 50),
(14, 'Bạn tên là gì?', 14, 0, 50),
(15, 'Tôi tên là Nam Anh', 15, 0, 50),
(16, 'Tôi sinh ra ở Mỹ', 16, 0, 50),
(17, 'Hôm nay trời nắng', 17, 0, 50),
(18, 'Tôi đợi bạn tới', 18, 0, 50),
(19, 'Tôi đi du lịch cùng bạn', 19, 0, 50),
(20, 'Chuyến bay thuận lợi', 20, 0, 50),
(21, 'Tôi làm nghề y tá', 21, 0, 60),
(22, 'Cho tôi đặt phòng hai ngày', 22, 0, 60),
(23, 'Chiếc váy một triệu đồng', 23, 0, 60),
(24, 'Tôi rất thích đọc sách', 24, 0, 60),
(25, 'Bạn có thích chơi thể thao không', 25, 0, 60),
(26, 'Ở Mỹ mọi người chủ yếu thích chơi bóng đá', 26, 0, 60),
(27, 'Người Việt Nam rất thân thiện', 27, 0, 60),
(28, 'Học tiếng Việt không đơn giản', 28, 0, 60),
(29, 'Tôi cần hỏi đường tới hồ Hoàn Kiếm', 29, 0, 60),
(30, 'Đây có phải đường tới khách sạn không', 30, 0, 60),
(51, 'Chào bạn\r\n', 1, 1, 50),
(52, 'Tôi rất khoẻ\r\n', 2, 1, 50),
(53, 'Hai mươi tuổi\r\n', 3, 1, 50),
(54, 'Hồ Tây\r\n', 4, 1, 50),
(55, 'Đi thẳng\r\n', 5, 1, 50),
(56, 'Vui vẻ\r\n', 6, 1, 50),
(57, 'Thân thiện\r\n', 7, 1, 50),
(58, 'Đèn xanh\r\n', 8, 1, 50),
(59, 'Điện thoại\r\n', 9, 1, 50),
(60, 'Dừng lại\r\n', 10, 1, 50),
(61, 'Ghé vào', 11, 1, 50),
(62, 'Bốn cây số', 12, 1, 50),
(63, 'Ngã ba rẽ trái', 13, 1, 50),
(64, 'Bến xe khách', 14, 1, 50),
(65, 'Cảm ơn bạn', 15, 1, 50),
(66, 'Mười nghìn đồng', 16, 1, 50),
(67, 'Tôi xin số điện thoại', 17, 1, 50),
(68, 'Gửi tin nhắn cho tôi', 18, 1, 50),
(69, 'Gửi lời kết bạn tới tôi', 19, 1, 50),
(70, 'Gần đây làm gì rồi', 20, 1, 50),
(71, 'Món phở rất ngon', 21, 1, 60),
(72, 'Nhà anh rất đẹp', 22, 1, 60),
(73, 'Khoảng hơn một tiếng', 23, 1, 60),
(74, 'Bây giờ mấy giờ', 24, 1, 60),
(75, 'Bố tôi làm công nhân ', 25, 1, 60),
(76, 'Tôi muốn đổi sang tiền Việt', 26, 1, 60),
(77, 'Tám giờ hai mươi phút tối', 27, 1, 60),
(78, 'Hẹn gặp anh vào ngày mai', 28, 1, 60),
(79, 'Đèn đỏ phía trước', 29, 1, 60),
(80, 'Chúc anh ngày mới tốt lành', 30, 1, 60),
(81, 'xin chào', 31, 1, 50);

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
(2, 'Xin chào anh', 'Hello', 2, 1),
(3, 'Chào em', 'Hello', 3, 1),
(4, 'Chào bạn', 'Hello', 4, 1),
(5, 'Chào thầy giáo', 'Hello teacher', 5, 1),
(6, 'Chào Mai ', 'Hi Mai', 6, 1),
(7, 'Chào tất cả mọi người', 'Hello everyone', 7, 1),
(8, 'Bạn có khoẻ không', 'How are you?', 8, 1),
(9, 'Rất vui được gặp bạn', 'Nice to meet you', 9, 1),
(10, 'Rất hận hạnh được gặp bạn', 'Nice to meet you', 10, 1),
(11, 'Bạn tên là gì', 'What is your name?', 1, 2),
(12, 'Tôi tên là Hoa', 'My name is Hoa', 2, 2),
(13, 'Tên của bạn là gì', 'What is your name?', 3, 2),
(14, 'Tên mình là Hoàng', 'My name is Hoàng', 4, 2),
(15, 'Tên đầy đủ của bạn là gì', 'What is your full name?', 5, 2),
(16, 'Tên đầy đủ của tôi là Dương Dương ', 'My full name is Duong Duong', 6, 2),
(17, 'Cho tôi xin tên của bạn', 'Give me your name', 7, 2),
(18, 'Bạn có khoẻ không', 'My name is Hoa', 8, 2),
(19, 'Rất vui được gặp bạn', 'I am Xuan and you?', 9, 2),
(20, 'Tên của bạn là Tiến phải không', 'Are you Tiến?', 10, 2),
(21, 'Ông nội', 'Grandfather', 1, 3),
(22, 'Bà', 'Grandmother', 2, 3),
(23, 'Bố', 'Father', 3, 3),
(24, 'Mẹ', 'Mother', 4, 3),
(25, 'Dì', 'Aunt', 5, 3),
(26, 'Chú', 'Uncle', 6, 3),
(27, 'Bác', 'Uncle', 7, 3),
(28, 'Anh trai', 'Brother', 8, 3),
(29, 'Chị gái', 'Sister', 9, 3),
(30, 'Anh họ', 'Cousin', 10, 3),
(31, 'Bạn tới từ đâu?', 'Where are you from?', 1, 4),
(32, 'Tôi tới từ Mỹ', 'I am from the US', 2, 4),
(33, 'Anh tới từ nước nào?', 'Which country are you from?', 3, 4),
(34, 'Tôi đến từ Anh', 'I am from England', 4, 4),
(35, 'Bạn là người nước ngoài à?', 'Are you a foreigner?', 5, 4),
(36, 'Tôi là người nước ngoài', 'I am foreigner', 6, 4),
(37, 'Tôi đến từ Việt Nam còn bạn?', 'I am from Vietnam and you?', 7, 4),
(38, 'Tôi đến từ Hàn Quốc', 'I am from Korea', 8, 4),
(39, 'Bạn sinh ra ở đâu?', 'Where were you born?', 9, 4),
(40, 'Tôi sinh ra và lớn lên ở Trung Quốc', 'I was born and raised in China', 10, 4),
(41, 'Giới thiệu với anh đây là anh Nam', 'Introduced to him this is Mr. Nam', 1, 5),
(42, 'Rất vui được làm quen với anh', 'Nice to meet you', 2, 5),
(43, 'Anh Nam trước đây làm gì?', 'What did Mr. Nam do before?', 3, 5),
(44, 'Anh Nam trước đây làm bác sĩ', 'Mr. Nam used to be a doctor', 4, 5),
(45, 'Trước đây tôi cũng làm bác sĩ', 'Previously I also worked as a doctor', 5, 5),
(46, 'Anh làm bác sĩ được bao lâu?', 'How long have you been a doctor?', 6, 5),
(47, 'Tôi là bác sĩ tám  năm', 'I am a doctor for eight years', 7, 5),
(48, 'Hiện tại anh đang làm gì?', 'What are you doing now?', 8, 5),
(49, 'Tôi đang làm kỹ sư công nghệ', 'I am a technology engineer', 9, 5),
(50, 'Hẹn gặp lại anh vào ngày sớm nhất', 'See you soon', 10, 5),
(51, 'Thời tiết chỗ bạn thế nào?', 'What is the weather like?', 1, 6),
(52, 'Hôm nay trời không đẹp lắm', 't is  not a very nice day', 2, 6),
(53, 'Hôm nay thời tiết đẹp quá', 'What a nice day', 3, 6),
(54, 'Trời bắt đầu mưa rồi', 'It is starting to rain', 4, 6),
(55, 'Trời nóng như thiêu', 'It is baking hot', 5, 6),
(56, 'Trời không gợn bóng mây', 'There is not a cloud in the sky', 6, 6),
(57, 'Trời u ám', 'The sky is overcast', 7, 6),
(58, 'Cậu nghĩ bây giờ đang bao nhiêu độ?', 'What temperature do you think it is?', 8, 6),
(59, 'Khoảng hai mươi độ', 'Probably about 20°C', 9, 6),
(60, 'Tối nay trời sẽ rất lạnh', ' It is going to freeze', 10, 6),
(61, 'Cho tôi hỏi cổng vào sân bay Nội Bài', 'Can I ask the entrance to Noi Bai airport?', 1, 7),
(62, 'Vui lòng cho tôi xem hộ chiếu', 'Please show me my passport', 2, 7),
(63, 'Cho tôi xem vé anh đã đặt', 'Show me the ticket you ordered', 3, 7),
(64, 'Anh có vé điện tử không?', 'Do you have an e ticket?', 4, 7),
(65, 'Tôi không có vé điện tử', 'I do not have an e ticket', 5, 7),
(66, 'Anh cần ký gửi hành lý không?', 'Do you need to check in your baggage?', 6, 7),
(67, 'Tôi cần ký gửi hành lý', 'I need to check in baggage', 7, 7),
(68, 'Tôi xách tay hành lý', 'I carry the luggage', 8, 7),
(69, 'Chuyến bay có bị hoãn không?', 'Is the flight delayed?', 9, 7),
(70, 'Chuyến bay bị hoãn ba mươi phút', 'The flight is delayed 30 minutes', 10, 7),
(71, 'Đây có phải khách sạn Mường Thanh không?', 'Is this Muong Thanh hotel?', 1, 8),
(72, 'Cho tôi hỏi chỗ đặt phòng', 'Can I ask for a reservation?', 2, 8),
(73, 'Cho tôi một đặt phòng ', 'Give me a reservation', 3, 8),
(74, 'Anh cần đặt phòng vào ngày nào?', 'What date do you need a reservation?', 4, 8),
(75, 'Tôi đặt phòng hôm nay', 'I book a room today', 5, 8),
(76, 'Giá phòng một ngày bao nhiêu tiền?', 'How much is the room rate per day?', 6, 8),
(77, 'Giá phòng một triệu một ngày', 'Room price one million a day', 7, 8),
(78, 'Cho tôi thanh toán', 'I paid for', 8, 8),
(79, 'Chìa khoá phòng của anh đây', 'Here is the key to my room', 9, 8),
(80, 'Rất vui được đón tiếp anh', 'Nice to welcome you', 10, 8),
(81, 'Cho tôi gọi đồ', 'I want to order', 1, 9),
(82, 'Tôi đang đợi bạn đến', 'I am waiting my friend', 2, 9),
(83, 'Anh chị dùng gì?', 'What would you drink?', 3, 9),
(84, 'Tôi uống coca', 'I drink coca', 4, 9),
(85, 'Tôi uống nước cam', 'I drink orange juice', 5, 9),
(86, 'Anh có muốn thêm đồ ăn nhẹ gì không?', 'Would you like more snacks?', 6, 9),
(87, 'Cho tôi một bánh bông lan', 'Give me a cake', 7, 9),
(88, 'Tôi ăn một bánh kem', 'I eat a cake', 8, 9),
(89, 'Bao lâu sẽ xong?', 'How long will it take?', 9, 9),
(90, 'Đồ của anh chị tới sau năm phút', 'Your stuff comes after five minutes', 10, 9),
(91, 'Chiếc mũ', 'Hat', 1, 10),
(92, 'Cho tôi xem cái mũ kia', 'Show me that hat', 2, 10),
(93, 'Chiếc váy hoa', 'The floral dress', 3, 10),
(94, 'Tôi muốn xem chiếc váy hoa này', 'I want to see this floral dress', 4, 10),
(95, 'Cái mũ này bao nhiêu tiền', 'How much is this hat', 5, 10),
(96, 'Giá cái mũ là một trăm nghìn đồng', 'The price of the hat is one hundred thousand dong', 6, 10),
(97, 'Giá của chiếc váy bao nhiêu?', 'How much is the dress?', 7, 10),
(98, 'Chiếc váy năm trăm nghìn', 'It cost hundred thousand ', 8, 10),
(99, 'Có váy cỡ rộng hơn không?', 'Is there a wider size skirt?', 9, 10),
(100, 'Cho tôi lấy váy và mũ', 'Let me get my skirt and hat', 10, 10),
(101, 'Xin lỗi cho tôi hỏi đường', 'Excuse me for directions', 1, 11),
(102, 'Anh cần hỏi đường tới đâu', 'You need to ask where to go', 2, 11),
(103, 'Tôi muốn tới lăng Hồ Chí Minh', 'I want to go to Ho Chi Minh mausoleum', 3, 11),
(104, 'Tới đó mất bao lâu?', 'How long will it take to get there?', 4, 11),
(105, 'Từ đây tới lăng Hồ Chí Minh hết mười phút', 'Ten minutes from here to Ho Chi Minh mausoleum', 5, 11),
(106, 'Đường đi như thế nào?', 'How is the road?', 6, 11),
(107, 'Đi thẳng', 'Go straight', 7, 11),
(108, 'Sau đó rẽ trái', 'Then turn left', 8, 11),
(109, 'Cảm ơn anh đã chỉ đường', 'Thank you for the directions', 9, 11),
(110, 'Không có gì', 'Nothing', 10, 11),
(111, 'Bạn có sở thích gì?', 'What are your hobbies?', 1, 12),
(112, 'Tôi thích chơi thể thao', 'I like play sports', 2, 12),
(113, 'Bạn làm gì vào thời gian rảnh?', 'How do you do in your free time?', 3, 12),
(114, 'Tôi thích xem phim khi rảnh', 'I like  watching movies', 4, 12),
(115, 'Tôi thích đá bóng khi rảnh', 'I like  play soccer', 5, 12),
(116, 'Sở thích của tôi là đi du lịch', 'My hobby is traveling', 6, 12),
(117, 'Tôi muốn đi du lịch vòng quanh thế giới', 'I want to travel around the world', 7, 12),
(118, 'Bạn có sở thích đọc sách không?', 'Do you enjoy reading? book', 8, 12),
(119, 'Tôi rất thích đọc sách', 'I really like reading books', 9, 12),
(120, 'Tôi hay đọc truyện hài', 'I often read comedy', 10, 12),
(121, 'Bạn có thích chơi thể thao không?', 'Do you like playing sports?', 1, 13),
(122, 'Tôi rất thích chơi thể thao', 'I really love playing sports', 2, 13),
(123, 'Tôi rất ghét chơi thể thao', 'I really hate playing sports', 3, 13),
(124, 'Tôi thích chơi bóng đá', 'I like soccer', 4, 13),
(125, 'Bạn có đam mê với môn thể thao gì?', 'What sport are you passionate about?', 5, 13),
(126, 'Môn thể thao đam mê là bóng chuyền', 'Favorite sport is volleyball', 6, 13),
(127, 'Ở Anh môn thể thao nào là phổ biến?', 'What sport is popular in the UK?', 7, 13),
(128, 'Người Anh thích chơi quần vợt', 'In England they love to play tennis', 8, 13),
(129, 'Ở Việt Nam mọi người thích môn thể thao nào?', 'What sport do people like in Vietnam?', 9, 13),
(130, 'Việt Nam chủ yếu thích bóng đá', 'Vietnam mainly likes football', 10, 13),
(131, 'Du lịch', 'Travel', 1, 14),
(132, 'Tôi rất thích đi du lịch', 'I like travelling', 2, 14),
(133, 'Bạn đã đi du lịch được bao nhiêu nước?', 'How many countries have you traveled to?', 3, 14),
(134, 'Tôi đã đi năm nước trên thế giới', 'I have traveled to five countries around the world', 4, 14),
(135, 'Bạn tới Việt Nam được bao lâu?', 'How long have you been to Vietnam?', 5, 14),
(136, 'Tôi tới Việt Nam hai tuần ', 'I went to Vietnam for two weeks', 6, 14),
(137, 'Bạn hay đi du lịch cùng với ai?', 'Who do you travel with?', 7, 14),
(138, 'Tôi thích đi du lịch một mình', 'I travel alone', 8, 14),
(139, 'Tôi đi du lịch với gia đình', 'I travel with my family', 9, 14),
(140, 'Tôi sẽ quay lại du lịch Việt Nam', 'I will return to Vietnam', 10, 14),
(141, 'Ấn tượng Việt Nam', 'Impressive Vietnam', 1, 15),
(142, 'Người Việt rất thân thiện', 'Vietnamese people are very friendly', 2, 15),
(143, 'Kem Tràng Tiền', 'Trang Tien ice cream', 3, 15),
(144, 'Trà đá vỉa hè', 'Ice tea on the street', 4, 15),
(145, 'Đặc sản phở Hà Nội', 'Hà Nội Noodle Soup', 5, 15),
(146, 'Độc đáo chợ nổi miền Tây', 'Unique Western floating market', 6, 15),
(147, 'Việt Nam có hang động lớn nhất thế giới', 'Vietnam has the largest cave in the world', 7, 15),
(148, 'Du lịch Việt Nam phong phú', 'Vietnamese tourism is abundant', 8, 15),
(149, 'Tết nguyên đán rực rỡ đèn hoa', 'Lunar New Year with brilliant flower lights', 9, 15),
(150, 'Học tiếng Việt không hề đơn giản', 'Learning Vietnamese is not easy', 10, 15),
(201, 'Xin chào', 'Hello', 1, 21),
(202, 'Xin chào anh', 'Hello', 2, 21),
(203, 'Chào chị', 'Hello', 3, 21),
(204, 'Chào bạn', 'Hello', 4, 21),
(205, 'Chào anh Nam', 'Hi Mr. Nam', 5, 21),
(206, 'Xin chào tất cả mọi người ', 'Hello everyone', 6, 21),
(207, 'Cô có khoẻ không?', 'How are you?', 7, 21),
(208, 'Tôi rất khoẻ', 'I am very healthy', 8, 21),
(209, 'Chúc một ngày tuyệt vời', 'Have a great day', 9, 21),
(210, 'Rất vui được làm quen', 'Nice to meet you', 10, 21),
(211, 'Tuổi', 'Age', 1, 22),
(212, 'Bạn bao nhiêu tuổi?', 'How old are you?', 2, 22),
(213, 'Mười tám tuổi', 'Eighteen years old', 3, 22),
(214, 'Sáu tám tuổi', 'Six eight years old', 4, 22),
(215, 'Năm nay tôi ba mươi tám tuổi', 'I am thirty-eight years old', 5, 22),
(216, 'Tôi hai mươi tuổi còn bạn?', 'I am twenty years old and you?', 6, 22),
(217, 'Tôi ba mươi tuổi', 'I am thirty years old', 7, 22),
(218, 'Sinh nhật bạn vào ngày nào?', 'What is your birthday?', 8, 22),
(219, 'Sinh nhật tên ngày mười tám ', 'My birthday is eighteen', 9, 22),
(220, 'Rất hận hạnh được gặp bạn', 'Eight months before my next birthday', 10, 22),
(221, 'Cam', 'Oranges', 1, 23),
(222, 'Cây cam', 'Orange', 2, 23),
(223, 'Vàng', 'Yellow', 3, 23),
(224, 'Màu vàng', 'Yellow', 4, 23),
(225, 'Biển', 'Sea', 5, 23),
(226, 'Cảnh biển ', 'Seascape', 6, 23),
(227, 'Quyết', 'Decision', 7, 23),
(228, 'Bí quyết', 'Recipes', 8, 23),
(229, 'Điện thoại', 'Phone', 9, 23),
(230, 'Chợ', 'Market', 10, 23),
(231, 'Hoa', 'Flower', 1, 24),
(232, 'Lá', 'Leaves', 2, 24),
(233, 'Cành', 'Branches', 3, 24),
(234, 'Học tập', 'Study', 4, 24),
(235, 'Lao động', 'Labor', 5, 24),
(236, 'Nghĩa vụ', 'Duty', 6, 24),
(237, 'Long lanh', 'Glitter', 7, 24),
(238, 'Nhấp nháy', 'Flicker', 8, 24),
(239, 'Mênh mông', 'Immensity', 9, 24),
(240, 'Thênh thang', 'Wandering', 10, 24),
(241, 'Bây giờ là mấy giờ?', 'What time is it?', 1, 25),
(242, 'Bây giờ là mười giờ', 'It is ten o clock', 2, 25),
(243, 'Đồng hồ mấy giờ rồi?', 'What time is the clock?', 3, 25),
(244, 'Chín giờ tối', 'Nine pm', 4, 25),
(245, 'Tám giờ mười năm phút', 'Eight o clock and ten minutes', 5, 25),
(246, 'Hôm nay là thứ mấy', 'What day is today?', 6, 25),
(247, 'Hôm nay là thứ hai', 'Today is Monday', 7, 25),
(248, 'Hôm nay là ngày mấy', 'What date is today?', 8, 25),
(249, 'Hôm nay là ngày mười một', 'Today is the eleventh day', 9, 25),
(250, 'Bây giờ là tám giờ có phải không?', 'It is eight o clock, is not it?', 10, 25),
(251, 'Bến xe', 'Bus station', 1, 26),
(252, 'Xe taxi ở đâu?', 'Where are the taxis?', 2, 26),
(253, 'Bến đậu xe taxi ở bên kia', 'The taxi park is on the other side', 3, 26),
(254, 'Anh đi đâu?', 'Where are you going to?', 4, 26),
(255, 'Tôi muốn đến khách sạn', 'I want to go to the hotel', 5, 26),
(256, 'Đến đấy bao xa?', 'How far is it?', 6, 26),
(257, 'Bốn cây số', 'Four kilometers', 7, 26),
(258, 'Bao nhiêu tiền?', 'How much?', 8, 26),
(259, 'Hai trăm nghìn đồng', 'Two hundred thousand dong', 9, 26),
(260, 'Dừng lại', 'Stop', 10, 26),
(261, 'Bưu điện', 'Post office', 1, 27),
(262, 'Xin chào cô', 'Hello lady', 2, 27),
(263, 'Tôi cần vài tấm bưu thiếp', 'I need some postcards', 3, 27),
(264, 'Tôi cần tám chiếc tem', 'I need eight stamps', 4, 27),
(265, 'Tôi muốn mua phong bì quốc tế', 'I want to buy international envelopes', 5, 27),
(266, 'Tôi cần gửi bưu phẩm sang Nga', 'I need to send the parcel to Russia', 6, 27),
(267, 'Bưu phí bao nhiêu?', 'How much postage?', 7, 27),
(268, 'Giá dịch vụ?', 'Service prices?', 8, 27),
(269, 'Đóng gói đồ cho tôi', 'Pack me up', 9, 27),
(270, 'Cho tôi phiếu thanh toán', 'Give me the payment slip', 10, 27),
(271, 'Gia đình', 'Family', 1, 28),
(272, 'Gia đình bạn có mấy người?', 'How many people are there in your family?', 2, 28),
(273, 'Gia đình tôi có năm người', 'There are five people in my family', 3, 28),
(274, 'Bố tôi năm mươi tuổi', 'My father is fifty years old', 4, 28),
(275, 'Mẹ tôi bốn mươi hai tuổi', 'My mother is forty two years old', 5, 28),
(276, 'Bố tôi làm công nhân', 'My father is a worker', 6, 28),
(277, 'Mẹ tôi làm bác sĩ', 'My mom is a doctor', 7, 28),
(278, 'Anh tôi ba mươi tuổi', 'My brother is thirty years old', 8, 28),
(279, 'Anh làm kỹ sư', 'He is an engineer', 9, 28),
(280, 'Em gái đang là học sinh', 'The sister is a student', 10, 28),
(281, 'Xin chào anh', 'Hello', 1, 29),
(282, 'Này anh bạn', 'Hey man', 2, 29),
(283, 'Dạo này khoẻ không?', 'How are you these days?', 3, 29),
(284, 'Tôi rất khoẻ', 'I am very healthy', 4, 29),
(285, 'Lâu lắm không gặp', 'Long time no see', 5, 29),
(286, 'Gia đình thế nào?', 'How is the family?', 6, 29),
(287, 'Gia đình tôi vẫn bình thường', 'My family is still normal', 7, 29),
(288, 'Ông nghỉ công ty cũ rồi à?', 'Did you quit the old company?', 8, 29),
(289, 'Tôi mới nghỉ được một tháng', 'I have only been off for a month', 9, 29),
(290, 'Hiện tại tôi đang làm kinh doanh', 'Currently I am doing business', 10, 29),
(291, 'Tiền', 'Money', 1, 30),
(292, 'Tiền Việt Nam', 'Vietnam Money', 2, 30),
(293, 'Tôi muốn đổi tiền', 'I want to change money', 3, 30),
(294, 'Anh muốn đổi sang loại tiền nào?', 'What currency do you want to convert to?', 4, 30),
(295, 'Tôi muốn đổi đô la sang tiền Việt', 'I want to convert dollars into Vietnam dong', 5, 30),
(296, 'Anh muốn đổi bao nhiêu tiền?', 'How much money do you want to exchange?', 6, 30),
(297, 'Tôi cần đổi một nghìn đô la', 'I need to exchange a thousand dollars', 7, 30),
(298, 'Anh muốn mệnh giá nào?', 'Which denomination do you want?', 8, 30),
(299, 'Tôi muốn mệnh giá hai trăm nghìn', 'I want par value two hundred thousand', 9, 30),
(300, 'Tôi cần tiền lẻ', 'I need the money in small denominations', 10, 30),
(301, 'Giải trí', 'Entertainment', 1, 31),
(302, 'Trò chơi giải trí', 'Entertaining game', 2, 31),
(303, 'Mỗi khi mệt mỏi anh làm gì?', 'What do you do when you are tired?', 3, 31),
(304, 'Tôi đi chạy bộ', 'I go jogging', 4, 31),
(305, 'Tôi thì thích đi vũ trường', 'I like to go to the dance hall', 5, 31),
(306, 'Tôi thích nghe nhạc để giải trí', 'I love listening to music for entertainment', 6, 31),
(307, 'Tôi chơi các thể loại game giải trí', 'I play entertaining games', 7, 31),
(308, 'Tôi đọc một quyển sách mới', 'I read a new book', 8, 31),
(309, 'Xem phim hài hước', 'Watch funny movies', 9, 31),
(310, 'Tôi vào bếp nấu ăn', 'I go to the kitchen to cook', 10, 31),
(311, 'Trường học', 'School', 1, 32),
(312, 'Chào cô giáo', 'Hi teacher', 2, 32),
(313, 'Em chào thầy giáo', 'Hello teacher', 3, 32),
(314, 'Hôm nay các bạn thế nào?', 'How are you today', 4, 32),
(315, 'Mời các bạn ngồi xuống', 'Please sit down', 5, 32),
(316, 'Có ai vắng mặt hôm nay?', 'Who is absent today?', 6, 32),
(317, 'Hoa vắng hôm nay', 'Hoa is absent today', 7, 32),
(318, 'Chẳng ai vắng hôm nay', 'Nobody is absent today', 8, 32),
(319, 'Xin phép em tới trễ', 'Sorry, I am late', 9, 32),
(320, 'Bắt đầu học tập', 'Start learning', 10, 32),
(321, 'Cảm xúc', 'Feeling', 1, 33),
(322, 'Vui vẻ\r\n', 'Happy', 2, 33),
(323, 'Hài hước', 'Humor', 3, 33),
(324, 'Nóng giận', 'Angry', 4, 33),
(325, 'Sợ hãi', 'Fear', 5, 33),
(326, 'Chán nản', 'Boring', 6, 33),
(327, 'Giận dữ', 'Angry', 7, 33),
(328, 'Ngạc nhiên', 'Surprise', 8, 33),
(329, 'Hi vọng', 'Hope', 9, 33),
(330, 'Tin tưởng', 'Trust', 10, 33),
(331, 'Nhà đẹp', 'Nice house', 1, 34),
(332, 'Anh mới có nhà mới à?', 'Do you have a new home?', 2, 34),
(333, 'Ngôi nhà rất đẹp', 'The house is very beautiful', 3, 34),
(334, 'Ngôi nhà rộng bao nhiêu mét?', 'How wide is the house?', 4, 34),
(335, 'Ban công thật nhiều cây xanh', 'Ban công thật nhiều cây xanh', 5, 34),
(336, 'Bức tranh thật sang trọng', 'The picture is very luxurious', 6, 34),
(337, 'Tầm nhìn đẹp quá', 'Nice view', 7, 34),
(338, 'Nhà bếp thật tiện nghi', 'Cozy living room', 8, 34),
(339, 'Hôm nay là ngày mười một', 'Today is the eleventh day', 9, 34),
(340, 'Sân vườn rộng', 'Large garden', 10, 34),
(341, 'Chào tạm biệt', 'Good bye', 1, 35),
(342, 'Chúc anh sức khoẻ', 'Wishing you health', 2, 35),
(343, 'Tạm biệt anh', 'Goodbye', 3, 35),
(344, 'Hẹn gặp anh vào ngày mai', 'See you tomorrow', 4, 35),
(345, 'Hẹn gặp lại', 'See you again', 5, 35),
(346, 'Cảm ơn anh ', 'Thank you', 6, 35),
(347, 'Tôi có chút việc bận', 'I am a bit busy', 7, 35),
(348, 'Tôi phải rời đi bây giờ', 'I have to leave now', 8, 35),
(349, 'Giữ gìn sức khoẻ nhé', 'Stay healthy', 9, 35),
(350, 'Chúc anh ngày mới tốt lành', 'Have a nice day', 10, 35);

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
  `phone` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `imageuser` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `speakinglevel` int(11) NOT NULL,
  `listeninglevel` int(11) NOT NULL,
  `checkadmin` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`iduser`, `name`, `username`, `password`, `phone`, `email`, `imageuser`, `speakinglevel`, `listeninglevel`, `checkadmin`) VALUES
(16, 'VŨ VĂN TIẾN', 'tienvu', '1234567', '0365544123', 'tienvu@gmail.com', 'https://tienvu1305.000webhostapp.com/image_user/tienvu.jpg', 30, 32, 1),
(21, 'VŨ BÁ CÔNG', 'vu', 'vu', '0978363411', 'vu@gmail.com', 'https://tienvu1305.000webhostapp.com/image_user/vu.jpg', 2, 2, 0),
(22, 'ĐÀO VĂN ANH', 't', 't', '0365544124', 't@gmail.com', 'https://tienvu1305.000webhostapp.com/image_user/t.jpg', 6, 7, 0),
(23, 'NGUYỄN THỊ HƯỜNG', 'q', 'q', '0365544125', 'q@gmail.com', 'https://tienvu1305.000webhostapp.com/image_user/q.jpg', 6, 6, 0),
(26, 'LÊ PHƯƠNG ANH', 'trang', 'trang', '0377845546', 'trang@gmail.com', 'https://tienvu1305.000webhostapp.com/image_user/trang.jpg', 3, 7, 0),
(30, 'HOANG TRANG', 'Trabg2808', '123456aB@', '0564111123', 'trangr@gmail.com', 'https://tienvu1305.000webhostapp.com/image_user/Trabg2808.jpg', 1, 1, 0),
(45, 'TRANGHOANGTHI', 'Tranghoang', '1234567', 'hoang@gmail.com', '0354222411', 'https://tienvu1305.000webhostapp.com/image_user/Tranghoang.jpg', 1, 1, 0),
(46, 'TRANGTH', 'TrangThi', '123456789', '0541777888', 'ly@gmail.com', 'https://tienvu1305.000webhostapp.com/image_user/TrangThi.jpg', 1, 1, 0),
(47, 'TRANG', 'tranght62@wru.vn', '1234567890', '0544477887', 'ht@gmail.com', 'https://tienvu1305.000webhostapp.com/image_user/tranght62@wru.vn.jpg', 20, 15, 0),
(49, 'HOÀNG TRANG', 'HTTrang', '1234567', '0654123411', 'hh@gmail.com', 'https://tienvu1305.000webhostapp.com/image_user/HTTrang.jpg', 5, 6, 0),
(50, 'HOA', 'HoaHT', '1234567', '0955444333', 'hoa@gmail.com', 'https://tienvu1305.000webhostapp.com/image_user/HoaHT.jpg', 8, 9, 0),
(51, 'VŨ TIẾN', 'qq', '1234567', '0978363417', 'q@gmail.com', 'https://tienvu1305.000webhostapp.com/image_user/qq.jpg', 1, 1, 0),
(52, 'TIEN', 'qqq', '1234567', '0967e63407', 'q@gmail.com', 'https://tienvu1305.000webhostapp.com/image_user/qqq.jpg', 1, 1, 0),
(53, 'L', 'qt', '1234567', '0967363407', 'p@gmail.com', 'https://tienvu1305.000webhostapp.com/image_user/qt.jpg', 1, 1, 0),
(61, 'HOA', 'y', '1234t67', '09783634177', 'qq@gmail.com', 'https://tienvu1305.000webhostapp.com/image_user/y.jpg', 1, 1, 0),
(62, 'TIEN', 'tw', '123r567', '0123456789', 'q@gmail.com', 'https://tienvu1305.000webhostapp.com/image_user/tw.jpg', 1, 1, 0),
(66, 'tien', 'vutien', '1234567', '0987555444', 'tien@gmail.com', 'https://tienvu1305.000webhostapp.com/image_user/vutien.jpg', 1, 1, 0),
(67, 'DEMO', 'demo', '123456789', '0912234567', 'dovanhaivtcc@gmail.com', 'https://tienvu1305.000webhostapp.com/image_user/demo.jpg', 1, 1, 0);

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
(126, 49, 51, 100, 1607235918815),
(127, 49, 51, 50, 1607235928884),
(128, 49, 51, 85, 1607235960106),
(129, 49, 52, 66, 1607235977997),
(130, 49, 53, 66, 1607235993758),
(131, 49, 54, 100, 1607236005106),
(132, 49, 55, 50, 1607236027064),
(133, 49, 1, 100, 1607236443420),
(134, 49, 2, 100, 1607236502581),
(135, 49, 2, 50, 1607236883886),
(136, 49, 3, 50, 1607236893638),
(137, 16, 1, 100, 1607237047363),
(138, 49, 4, 100, 1607237109287),
(139, 16, 58, 100, 1607241991563),
(140, 16, 59, 100, 1607242147497),
(141, 16, 60, 100, 1607242194400),
(142, 16, 61, 100, 1607272024282),
(143, 16, 6, 100, 1607272047958),
(144, 16, 7, 100, 1607272065651),
(145, 16, 62, 100, 1607272533130),
(146, 16, 79, 100, 1607274704904),
(147, 16, 80, 100, 1607274856060),
(148, 16, 29, 100, 1607274957926),
(149, 22, 4, 100, 1607276312072),
(150, 22, 5, 100, 1607276331941),
(151, 16, 81, 100, 1607306037729),
(152, 50, 1, 100, 1607422112188),
(153, 50, 2, 50, 1607422123939),
(154, 50, 3, 100, 1607422181413),
(155, 50, 4, 100, 1607422262510),
(156, 50, 5, 50, 1607422312570),
(157, 50, 6, 100, 1607422408632),
(158, 50, 6, 100, 1607422558346),
(159, 50, 7, 100, 1607422656042),
(160, 50, 51, 50, 1607422779785),
(161, 50, 51, 90, 1607422882054),
(162, 50, 51, 90, 1607422970114),
(163, 50, 1, 100, 1607435599403);

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
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `phone` (`phone`);

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
  MODIFY `idquestion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=82;

--
-- AUTO_INCREMENT cho bảng `questiontopics`
--
ALTER TABLE `questiontopics`
  MODIFY `idquestiontopic` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=351;

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
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT cho bảng `users_questions`
--
ALTER TABLE `users_questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=164;

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
