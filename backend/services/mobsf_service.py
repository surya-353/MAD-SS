import requests
from config.settings import MOBSF_BASE_URL, MOBSF_API_KEY

def run_mobsf_analysis(apk_file):
    headers = {'Authorization': MOBSF_API_KEY}
    with open(apk_file, 'rb') as f:
        files = {'file': f}
        response = requests.post(f'{MOBSF_BASE_URL}upload', headers=headers, files=files)
        if response.status_code == 200:
            return response.json()
        else:
            return {'error': 'MobSF Analysis Failed'}
