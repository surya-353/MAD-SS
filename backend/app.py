from flask import Flask, request, jsonify, render_template
from services.mobsf_service import run_mobsf_analysis
from services.frida_service import run_frida_analysis
from services.qark_service import run_qark_analysis

app = Flask(__name__)

@app.route('/upload', methods=['POST'])
def upload_apk():
    if 'file' not in request.files:
        return jsonify({'error': 'No file part'}), 400

    file = request.files['file']

    # Save file temporarily
    file_path = f"./uploads/{file.filename}"
    file.save(file_path)

    # Run analyses
    mobsf_result = run_mobsf_analysis(file_path)
    frida_result = run_frida_analysis(file_path)
    qark_result = run_qark_analysis(file_path)

    return render_template('report_template.html', mobsf_result=mobsf_result, frida_result=frida_result, qark_result=qark_result)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
