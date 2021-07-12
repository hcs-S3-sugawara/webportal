package jp.ac.hcs.s3a117.user;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;


	@Transactional
	@Service
	@Slf4j
	public class UserService {
		
		@Autowired
		RestTemplate restTemplate;
		
		@Autowired
		UserRepository userRepository;
		
		/**
		 * サーバーに保存されているファイルを取得して表示する.
		 * @return ユーザエンティティ
		 * @throws IOException ファイル取得エラー
		 */
		public UserEntity selectAll() {
			UserEntity userEntity;
			try {
				userEntity = userRepository.selectAll();
			}catch (DataAccessException e) {
				e.printStackTrace();
				userEntity = null;
			}
			return userEntity;
		}

		/**
		 * ユーザを追加する
		 * @param userData ユーザの情報
		 * @return ユーザのid
		 * @throws IOException ファイル取得エラー
		 */
		public boolean insertOne(UserData userData) {
				int rowNumber;
			
				try {
					rowNumber = userRepository.insertOne(userData);
				}catch (DataAccessException e) {
					e.printStackTrace();
					rowNumber = 0;
				}
				return rowNumber > 0;
			}
		
		/**
		 * 入力項目をUserDataへ変換する
		 * @param from 入力データ
		 * @return UserData
	
		 */
		UserData refillToData(UserForm form) {
			
			UserData data = new UserData();
			
			data.setUser_id(form.getUser_id());
			data.setPassword(form.getPassword());
			data.setUser_name(form.getUser_name());
			data.setDarkmode(form.isDarkmode());
			data.setRole(form.getRole());
			data.setEnabled(true);
			
			return data;
		}
		
		
		/**
		 * 保存されているユーザの詳細を取得する
		 * @param user_id ユーザid
		 * @return UserData
		 * @throws IOException ファイル取得エラー
		 */
		public UserData selectOne(String user_id) {
			UserData data = new UserData();
			try {
				data = userRepository.selectOne(user_id);
			}catch (DataAccessException e) {
				e.printStackTrace();
			}
			return data;
		}
		
		/**
		 * 取得したユーザを削除する
		 * @param user_id ユーザid
		 * @throws IOException ファイル取得エラー
		 */
		public void deleteOne(String user_id){

			try {
				int rowNumber = userRepository.deleteOne(user_id);
			}catch (DataAccessException e) {
				e.printStackTrace();

		}
	}


	
		
}
