import axios from 'axios';

export const axiosPost = async (url: string, data: any) => {
  try {
    const response = await axios.post(url, data);
    return response;
  } catch (error) {
    console.error(error);
    alert('An error occurred!');
  }
};